package lib.ui.android;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTION_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_MY_LIST = "xpath://android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        VIEW_LIST = "id:org.wikipedia:id/snackbar_action";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        ARTICLE_TITLE_ID = "id:org.wikipedia:id/view_page_title_text";}
    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}