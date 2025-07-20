import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UrlTools {
    
    /**
     * 주어진 문자열이 URL 패턴과 일치하는지 확인합니다.
     */
    public static boolean isValidUrl(String url) {
        Pattern pattern = Pattern.compile(
            "^https?://" +  // http:// 또는 https://
            "[^\\s/$.?#].[^\\s]*$"  // 도메인과 경로
        );
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
    
    /**
     * URL이 'https://'로 시작하도록 합니다.
     */
    public static String formatUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return "https://" + url;
        }
        return url;
    }
    
    /**
     * URL을 지정된 길이로 잘라내고 '...'을 추가합니다.
     */
    public static String shortenUrl(String url, int length) {
        if (url.length() > length) {
            return url.substring(0, length) + "...";
        } else {
            return url;
        }
    }
    
    /**
     * 기본 길이 20으로 URL을 줄이는 오버로드된 메서드
     */
    public static String shortenUrl(String url) {
        return shortenUrl(url, 20);
    }
}
