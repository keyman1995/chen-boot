package com.chen.boot.chenboot.controller;


import com.chen.boot.chenboot.controlleradvice.DoubleColorService;
import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

@Component
public class DoubleColorUtils {

    @Autowired
    private ThreadPoolExecutor executorService;

    @Autowired
    private DoubleColorService doubleColorService;

    private volatile static AtomicInteger atomicInteger = new AtomicInteger();

    private static List<DoubleColorBallEntiry> doubleColorBallEntiries = new LinkedList<>();

    /**
     * 获取最新一期的数据
     *
     * @return
     */
    public DoubleColorBallEntiry  getCurrentTerm() {
        DoubleColorBallEntiry entiry = new DoubleColorBallEntiry();
        long recentDate = doubleColorService.getRecentDate();
        System.out.println("正在获取...");
        String homeUrl = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_1.html";
        String pageContent = getHtmlString(homeUrl);
        if (pageContent != null && !pageContent.equals("")) {
            getOneTermContent(pageContent);
        } else {
            System.out.println("该链接：url" + homeUrl + "的数据 丢失。");
        }
        //不为空的时候 取最新一期的号码
        if (!CollectionUtils.isEmpty(doubleColorBallEntiries)) {
            List<DoubleColorBallEntiry> emptyDoubleColorBallEntiries = new ArrayList<>();
            emptyDoubleColorBallEntiries = checkMaxTermDoubleColorBallEntity(doubleColorBallEntiries, recentDate, emptyDoubleColorBallEntiries);
            if (!CollectionUtils.isEmpty(emptyDoubleColorBallEntiries)) {
                doubleColorService.batchAdd(emptyDoubleColorBallEntiries);
            }
            doubleColorBallEntiries.clear();
        }

        return entiry;
    }


    //递归 遍历 当前列表中 大于今天的数据
    public List<DoubleColorBallEntiry> checkMaxTermDoubleColorBallEntity(List<DoubleColorBallEntiry> doubleColorBallEntiries, long maxOpenDate, List<DoubleColorBallEntiry> emptyDoubleColorEntiries) {
        IntSummaryStatistics statistics = doubleColorBallEntiries.stream().mapToInt((item) -> item.getOpenDate()).summaryStatistics();
        int currentDate = statistics.getMax();
        DoubleColorBallEntiry entiry;
        if (maxOpenDate < currentDate) {
            entiry = doubleColorBallEntiries.stream().filter((item) -> item.getOpenDate() == currentDate).collect(Collectors.toList()).get(0);
            emptyDoubleColorEntiries.add(entiry);
            doubleColorBallEntiries.remove(entiry);
            checkMaxTermDoubleColorBallEntity(doubleColorBallEntiries, maxOpenDate, emptyDoubleColorEntiries);
        }
        return emptyDoubleColorEntiries;
    }


    public String doCollect() {
        System.out.println("正在获取...");
        String baseUrlPrefix = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_";
        String baseUrlSuffix = ".html";
        String homeUrl = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_1.html";
        String pageCountContent = getHtmlString(homeUrl);
        int pageCount = getPageCount(pageCountContent);

        if (pageCount > 0) {
            for (int i = 1; i <= pageCount; i++) {
                String url = baseUrlPrefix + i + baseUrlSuffix;
                String pageContent = getHtmlString(url);
                if (pageContent != null && !pageContent.equals("")) {
                    getOneTermContent(pageContent);
                } else {
                    System.out.println("该链接：url" + url + "的数据 丢失。");
                }


//                executorService.execute(new DoubleColorBallTask(url, resultList));
//                if (i % NCPU == 0 || i == pageCount) {
//                    Future<Set<String>> futureResult = executorService.submit(new DoubleColorBallCallBack(url, resultList));
//                    Set<String> list = futureResult.get();
//                }
            }
        } else {
            System.out.println("结果页数为0");
        }
        System.out.println("完成！最终结果条数" + doubleColorBallEntiries);
        doubleColorService.batchAdd(doubleColorBallEntiries);
        return "SUCCESS";
    }


    /**
     * 获取总页数
     *
     * @param result
     */
    private static int getPageCount(String result) {
        String regex = "\\d+\">末页";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result);
        String[] splits = null;
        while (matcher.find()) {
            String content = matcher.group();
            splits = content.split("\"");
            break;
        }
        if (splits != null && splits.length == 2) {
            String countString = splits[0];
            if (countString != null && !countString.equals("")) {
                return Integer.parseInt(countString);
            }

        }
        return 0;
    }

    /**
     * 获取网页源码
     *
     * @return
     */
    private static String getHtmlString(String targetUrl) {
        String content = null;

        HttpURLConnection connection = null;
        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)");
            connection.setRequestProperty("Accept", "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
            connection.setRequestProperty("Accept-Language", "zh-cn");
            connection.setRequestProperty("UA-CPU", "x86");
            //为什么没有deflate呢
            connection.setRequestProperty("Accept-Encoding", "gzip");
            connection.setRequestProperty("Content-type", "text/html");
            //keep-Alive，有什么用呢，你不是在访问网站，你是在采集。嘿嘿。减轻别人的压力，也是减轻自己。
            connection.setRequestProperty("Connection", "close");
            //不要用cache，用了也没有什么用，因为我们不会经常对一个链接频繁访问。（针对程序）
            connection.setUseCaches(false);
            connection.setConnectTimeout(60 * 1000);
            connection.setReadTimeout(60 * 1000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Charset", "utf-8");

            connection.connect();

            if (200 == connection.getResponseCode()) {
                InputStream inputStream = null;
                if (connection.getContentEncoding() != null && !connection.getContentEncoding().equals("")) {
                    String encode = connection.getContentEncoding().toLowerCase();
                    if (encode != null && !encode.equals("") && encode.indexOf("gzip") >= 0) {
                        inputStream = new GZIPInputStream(connection.getInputStream());
                    }
                }

                if (null == inputStream) {
                    inputStream = connection.getInputStream();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                content = builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return content;
    }

    public static void startParseConten(Matcher matcher) {
        DoubleColorBallEntiry entiry = new DoubleColorBallEntiry();
        String oneTermContent = matcher.group();
        getOneTermDate(oneTermContent, entiry);
        getTheTerm(oneTermContent, entiry);
        getOneTermFirstPrize(oneTermContent, entiry);
        getOneTermSendPrize(oneTermContent, entiry);
        getOneTermPrize(oneTermContent, entiry);
        getOneTermNumbers(oneTermContent, entiry);
    }


    private static void getOneTermContent(String pageContent) {

        String regx = "<td align=\"center\">\\d+\\-\\d+\\-\\d+</td>[\\s\\S]+?<td align=\"center\"><strong class=\"rc\">\\d+</strong></td>";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(pageContent);
        while (matcher.find()) {
            startParseConten(matcher);
        }
    }

    private static void getOneTermSendPrize(String oneTermContent, DoubleColorBallEntiry entiry) {
        String regx = "<strong class=\"rc\">\\d+</strong>";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(oneTermContent);
        while (matcher.find()) {
            getSecondPrize(matcher.group(), entiry);
        }
    }

    private static void getSecondPrize(String secondContent, DoubleColorBallEntiry entiry) {
        String regx = "\\d+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(secondContent);
        while (matcher.find()) {
            entiry.setSecondPrize(Integer.valueOf(matcher.group()));
        }
    }

    /**
     * 获取该期的开奖日期
     *
     * @param oneTermContent
     */
    private static void getOneTermDate(String oneTermContent, DoubleColorBallEntiry entiry) {
        String dateRegx = "\\d+\\-\\d+\\-\\d+";
        Pattern pattern = Pattern.compile(dateRegx);
        Matcher matcher = pattern.matcher(oneTermContent);
        while (matcher.find()) {

            entiry.setOpenDate(Integer.valueOf(matcher.group().replaceAll("\\-", "")));
        }
    }

    /**
     * 获取改期 期号
     *
     * @param oneTermContent
     */
    private static void getTheTerm(String oneTermContent, DoubleColorBallEntiry entiry) {
        String termRegx = "<td align=\"center\">\\d+</td>";
        Pattern pattern = Pattern.compile(termRegx);
        Matcher matcher = pattern.matcher(oneTermContent);
        while (matcher.find()) {
            getTerm(matcher.group(), entiry);
        }
    }

    private static void getTerm(String termConten, DoubleColorBallEntiry entiry) {
        String termRegx = "\\d+";
        Pattern pattern = Pattern.compile(termRegx);
        Matcher matcher = pattern.matcher(termConten);
        while (matcher.find()) {
            entiry.setOpenTerm(Integer.valueOf(matcher.group()));
        }
    }


    /**
     * 获取该期二等奖人数
     *
     * @param oneTermContent
     */
    private static void getOneTermFirstPrize(String oneTermContent, DoubleColorBallEntiry entiry) {
        String regx = "<strong>\\d+</strong>";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(oneTermContent);
        while (matcher.find()) {
            String contents = matcher.group();
            getFirstPrize(contents, entiry);
        }
    }

    private static void getFirstPrize(String firstPrizeContent, DoubleColorBallEntiry entiry) {
        String regx = "\\d+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(firstPrizeContent);
        while (matcher.find()) {
            entiry.setFirstPrize(Integer.valueOf(matcher.group()));
        }
    }

    /**
     * 获取改期中奖金额
     *
     * @param oneTermContent
     */
    private static void getOneTermPrize(String oneTermContent, DoubleColorBallEntiry entiry) {
        String regx = ">\\d+\\,\\d+\\,\\d+<";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(oneTermContent);
        while (matcher.find()) {
            String content = matcher.group();
            String prize = content.substring(1, content.length() - 1);
            String finalPrize = prize.replaceAll("\\,", "").trim();
            entiry.setSales(Integer.valueOf(finalPrize));
        }
    }


    /**
     * 获取中奖号码
     *
     * @param oneTermContent
     * @param
     * @return
     */
    private static void getOneTermNumbers(String oneTermContent, DoubleColorBallEntiry entiry) {
        String regex = "<td align=\"center\" style=\"padding-left:10px;\">[\\s\\S]+?</em></td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(oneTermContent);
        StringBuffer finalResut = new StringBuffer();
        while (matcher.find()) {
            String content = matcher.group();
            getTermNumber(content, entiry);
        }

    }

    private static void getTermNumber(String termContent, DoubleColorBallEntiry entiry) {
        String regx = ">\\d+<";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(termContent);
        StringBuffer finalResut = new StringBuffer();

        while (matcher.find()) {
            String content = matcher.group();
            String ballNumber = content.substring(1, content.length() - 1);
            finalResut.append(ballNumber).append(" ");
        }
        addBallNumber(finalResut, entiry);
    }

    private static void addBallNumber(StringBuffer finalResut, DoubleColorBallEntiry entiry) {
        String[] balls = finalResut.toString().split(" ");
        if (balls.length == 7) {
            entiry.setFirstBall(Integer.valueOf(balls[0]));
            entiry.setSecondBall(Integer.valueOf(balls[1]));
            entiry.setThirdBall(Integer.valueOf(balls[2]));
            entiry.setFourthBall(Integer.valueOf(balls[3]));
            entiry.setFifthBall(Integer.valueOf(balls[4]));
            entiry.setSixthBall(Integer.valueOf(balls[5]));
            entiry.setBlueBall(Integer.valueOf(balls[6]));
        } else {
            System.err.println("改期采集的数据有误" + entiry);
        }
        doubleColorBallEntiries.add(entiry);
    }
}
