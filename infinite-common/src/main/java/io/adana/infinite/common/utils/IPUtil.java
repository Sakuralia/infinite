package io.adana.infinite.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @Author simon
 * @Email merin@outlook.com
 * @Date 2019/2/27
 * @Description get the ip address and mac address
 */
public class IPUtil {
    private static Logger logger = LoggerFactory.getLogger(IPUtil.class);

    /**
     * 获取当前网络ip
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ipAddress) || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ipAddress) || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    logger.error("UnknownHost," + e);
                }
                ipAddress = Objects.requireNonNull(inet).getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (StringUtils.isEmpty(ipAddress) && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获得MAC地址
     *
     * @param ip ip address
     * @return mac address
     */
    public static String getMACAddress(String ip) {
        String str;
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("MAC Address") > 1) {
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }

}
