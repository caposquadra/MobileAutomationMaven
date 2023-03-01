package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject  extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTION_BUTTON = "css:#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        ADD_TO_MY_LIST_BUTTON = "css:#page-actions-watch a#ca-watch";
        OPTION_REMOVE_FROM_MY_LIST_BUTTON ="css:#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        VIEW_LIST = "id:org.wikipedia:id/snackbar_action";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        ARTICLE_TITLE_ID = "id:org.wikipedia:id/view_page_title_text";}
    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}