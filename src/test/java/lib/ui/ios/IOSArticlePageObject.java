package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "//*[@text='View page in browser']";
        OPTION_BUTTON = "//android.widget.ImageView[@content-desc='More options']";
        ADD_TO_MY_LIST = "//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "//*[@text='OK']";
        VIEW_LIST = "org.wikipedia:id/snackbar_action";
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
        ARTICLE_TITLE_ID = "org.wikipedia:id/view_page_title_text";}
    public IOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}