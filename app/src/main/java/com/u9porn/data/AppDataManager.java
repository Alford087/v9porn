package com.u9porn.data;

import com.danikula.videocache.HttpProxyCacheServer;
import com.u9porn.data.db.DbHelper;
import com.u9porn.data.model.BaseResult;
import com.u9porn.data.db.entity.Category;
import com.u9porn.data.model.F9PronItem;
import com.u9porn.data.model.MeiZiTu;
import com.u9porn.data.model.Mm99;
import com.u9porn.data.model.Notice;
import com.u9porn.data.model.PavModel;
import com.u9porn.data.model.PavVideoParserJsonResult;
import com.u9porn.data.model.PinnedHeaderEntity;
import com.u9porn.data.model.F9PornContent;
import com.u9porn.data.model.ProxyModel;
import com.u9porn.data.db.entity.V9PornItem;
import com.u9porn.data.model.UpdateVersion;
import com.u9porn.data.model.User;
import com.u9porn.data.model.VideoComment;
import com.u9porn.data.db.entity.VideoResult;
import com.u9porn.data.network.ApiHelper;
import com.u9porn.data.prefs.PreferencesHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * @author flymegoc
 * @date 2017/11/22
 * @describe
 */

@Singleton
public class AppDataManager implements DataManager {

    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    private final HttpProxyCacheServer httpProxyCacheServer;

    @Inject
    AppDataManager(DbHelper mDbHelper, PreferencesHelper mPreferencesHelper, ApiHelper mApiHelper, HttpProxyCacheServer httpProxyCacheServer) {
        this.mDbHelper = mDbHelper;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mApiHelper = mApiHelper;
        this.httpProxyCacheServer = httpProxyCacheServer;
    }

    @Override
    public void initCategory(int type, String[] value, String[] name) {
        mDbHelper.initCategory(type, value, name);
    }

    @Override
    public void updateV9PornItem(V9PornItem v9PornItem) {
        mDbHelper.updateV9PornItem(v9PornItem);
    }

    @Override
    public List<V9PornItem> loadDownloadingData() {
        return mDbHelper.loadDownloadingData();
    }

    @Override
    public List<V9PornItem> loadFinishedData() {
        return mDbHelper.loadFinishedData();
    }

    @Override
    public List<V9PornItem> loadHistoryData(int page, int pageSize) {
        return mDbHelper.loadHistoryData(page, pageSize);
    }

    @Override
    public long saveV9PornItem(V9PornItem v9PornItem) {
        return mDbHelper.saveV9PornItem(v9PornItem);
    }

    @Override
    public long saveVideoResult(VideoResult videoResult) {
        return mDbHelper.saveVideoResult(videoResult);
    }

    @Override
    public V9PornItem findV9PornItemByViewKey(String viewKey) {
        return mDbHelper.findV9PornItemByViewKey(viewKey);
    }

    @Override
    public V9PornItem findV9PornItemByDownloadId(int downloadId) {
        return mDbHelper.findV9PornItemByDownloadId(downloadId);
    }

    @Override
    public List<V9PornItem> loadV9PornItems() {
        return mDbHelper.loadV9PornItems();
    }

    @Override
    public List<V9PornItem> findV9PornItemByDownloadStatus(int status) {
        return mDbHelper.findV9PornItemByDownloadStatus(status);
    }

    @Override
    public List<Category> loadAllCategoryDataByType(int type) {
        return mDbHelper.loadAllCategoryDataByType(type);
    }

    @Override
    public List<Category> loadCategoryDataByType(int type) {
        return mDbHelper.loadCategoryDataByType(type);
    }

    @Override
    public void updateCategoryData(List<Category> categoryList) {
        mDbHelper.updateCategoryData(categoryList);
    }

    @Override
    public Category findCategoryById(Long id) {
        return mDbHelper.findCategoryById(id);
    }

    @Override
    public Observable<List<V9PornItem>> loadPorn9VideoIndex(boolean cleanCache) {
        return mApiHelper.loadPorn9VideoIndex(cleanCache);
    }

    @Override
    public Observable<BaseResult<List<V9PornItem>>> loadPorn9VideoByCategory(String category, String viewType, int page, String m, boolean cleanCache, boolean isLoadMoreCleanCache) {
        return mApiHelper.loadPorn9VideoByCategory(category, viewType, page, m, cleanCache, isLoadMoreCleanCache);
    }

    @Override
    public Observable<BaseResult<List<V9PornItem>>> loadPorn9authorVideos(String uid, String type, int page, boolean cleanCache) {
        return mApiHelper.loadPorn9authorVideos(uid, type, page, cleanCache);
    }

    @Override
    public Observable<BaseResult<List<V9PornItem>>> loadPorn9VideoRecentUpdates(String next, int page, boolean cleanCache, boolean isLoadMoreCleanCache) {
        return mApiHelper.loadPorn9VideoRecentUpdates(next, page, cleanCache, isLoadMoreCleanCache);
    }

    @Override
    public Observable<VideoResult> loadPorn9VideoUrl(String viewKey) {
        return mApiHelper.loadPorn9VideoUrl(viewKey);
    }

    @Override
    public Observable<List<VideoComment>> loadPorn9VideoComments(String videoId, int page, String viewKey) {
        return mApiHelper.loadPorn9VideoComments(videoId, page, viewKey);
    }

    @Override
    public Observable<String> commentPorn9Video(String cpaintFunction, String comment, String uid, String vid, String viewKey, String responseType) {
        return mApiHelper.commentPorn9Video(cpaintFunction, comment, uid, vid, viewKey, responseType);
    }

    @Override
    public Observable<String> replyPorn9VideoComment(String comment, String username, String vid, String commentId, String viewKey) {
        return mApiHelper.replyPorn9VideoComment(comment, username, vid, commentId, viewKey);
    }

    @Override
    public Observable<BaseResult<List<V9PornItem>>> searchPorn9Videos(String viewType, int page, String searchType, String searchId, String sort) {
        return mApiHelper.searchPorn9Videos(viewType, page, searchType, searchId, sort);
    }

    @Override
    public Observable<String> favoritePorn9Video(String uId, String videoId, String ownnerId) {
        return mApiHelper.favoritePorn9Video(uId, videoId, ownnerId);
    }

    @Override
    public Observable<BaseResult<List<V9PornItem>>> loadPorn9MyFavoriteVideos(String userName, int page, boolean cleanCache) {
        return mApiHelper.loadPorn9MyFavoriteVideos(userName, page, cleanCache);
    }

    @Override
    public Observable<List<V9PornItem>> deletePorn9MyFavoriteVideo(String rvid) {
        return mApiHelper.deletePorn9MyFavoriteVideo(rvid);
    }

    @Override
    public Observable<User> userLoginPorn9Video(String username, String password, String captcha) {
        return mApiHelper.userLoginPorn9Video(username, password, captcha);
    }

    @Override
    public Observable<User> userRegisterPorn9Video(String username, String password1, String password2, String email, String captchaInput) {
        return mApiHelper.userRegisterPorn9Video(username, password1, password2, email, captchaInput);
    }

    @Override
    public Observable<List<PinnedHeaderEntity<F9PronItem>>> loadPorn9ForumIndex() {
        return mApiHelper.loadPorn9ForumIndex();
    }

    @Override
    public Observable<BaseResult<List<F9PronItem>>> loadPorn9ForumListData(String fid, int page) {
        return mApiHelper.loadPorn9ForumListData(fid, page);
    }

    @Override
    public Observable<F9PornContent> loadPorn9ForumContent(Long tid, boolean isNightModel) {
        return mApiHelper.loadPorn9ForumContent(tid, isNightModel);
    }

    @Override
    public Observable<UpdateVersion> checkUpdate() {
        return mApiHelper.checkUpdate();
    }

    @Override
    public Observable<Notice> checkNewNotice() {
        return mApiHelper.checkNewNotice();
    }

    @Override
    public Observable<BaseResult<List<MeiZiTu>>> listMeiZiTu(String tag, int page, boolean pullToRefresh) {
        return mApiHelper.listMeiZiTu(tag, page, pullToRefresh);
    }

    @Override
    public Observable<List<String>> meiZiTuImageList(int id, boolean pullToRefresh) {
        return mApiHelper.meiZiTuImageList(id, pullToRefresh);
    }

    @Override
    public Observable<BaseResult<List<Mm99>>> list99Mm(String category, int page, boolean cleanCache) {
        return mApiHelper.list99Mm(category, page, cleanCache);
    }

    @Override
    public Observable<List<String>> mm99ImageList(int id, String imageUrl, boolean pullToRefresh) {
        return mApiHelper.mm99ImageList(id, imageUrl, pullToRefresh);
    }

    @Override
    public Observable<List<PavModel>> loadPavListByCategory(String category, boolean pullToRefresh) {
        return mApiHelper.loadPavListByCategory(category, pullToRefresh);
    }

    @Override
    public Observable<List<PavModel>> loadMorePavListByCategory(String category, int page, boolean pullToRefresh) {
        return mApiHelper.loadMorePavListByCategory(category, page, pullToRefresh);
    }

    @Override
    public Observable<PavVideoParserJsonResult> loadPavVideoUrl(String url, String pId, boolean pullToRefresh) {
        return mApiHelper.loadPavVideoUrl(url, pId, pullToRefresh);
    }

    @Override
    public Observable<BaseResult<List<ProxyModel>>> loadXiCiDaiLiProxyData(int page) {
        return mApiHelper.loadXiCiDaiLiProxyData(page);
    }

    @Override
    public Observable<Boolean> testProxy(String proxyIpAddress, int proxyPort) {
        return mApiHelper.testProxy(proxyIpAddress, proxyPort);
    }

    @Override
    public void setPorn9VideoAddress(String address) {
        mPreferencesHelper.setPorn9VideoAddress(address);
    }

    @Override
    public String getPorn9VideoAddress() {
        return mPreferencesHelper.getPorn9VideoAddress();
    }

    @Override
    public void setPorn9ForumAddress(String address) {
        mPreferencesHelper.setPorn9ForumAddress(address);
    }

    @Override
    public String getPorn9ForumAddress() {
        return mPreferencesHelper.getPorn9ForumAddress();
    }

    @Override
    public void setPavAddress(String address) {
        mPreferencesHelper.setPavAddress(address);
    }

    @Override
    public String getPavAddress() {
        return mPreferencesHelper.getPavAddress();
    }

    @Override
    public void setPorn9VideoLoginUserName(String userName) {
        mPreferencesHelper.setPorn9VideoLoginUserName(userName);
    }

    @Override
    public String getPorn9VideoLoginUserName() {
        return mPreferencesHelper.getPorn9VideoLoginUserName();
    }

    @Override
    public void setPorn9VideoLoginUserPassWord(String passWord) {
        mPreferencesHelper.setPorn9VideoLoginUserPassWord(passWord);
    }

    @Override
    public String getPorn9VideoLoginUserPassword() {
        return mPreferencesHelper.getPorn9VideoLoginUserPassword();
    }

    @Override
    public void setPorn9VideoUserAutoLogin(boolean autoLogin) {
        mPreferencesHelper.setPorn9VideoUserAutoLogin(autoLogin);
    }

    @Override
    public boolean isPorn9VideoUserAutoLogin() {
        return mPreferencesHelper.isPorn9VideoUserAutoLogin();
    }

    @Override
    public void setFavoriteNeedRefresh(boolean needRefresh) {
        mPreferencesHelper.setFavoriteNeedRefresh(needRefresh);
    }

    @Override
    public boolean isFavoriteNeedRefresh() {
        return mPreferencesHelper.isFavoriteNeedRefresh();
    }

    @Override
    public void setPlaybackEngine(int playbackEngine) {
        mPreferencesHelper.setPlaybackEngine(playbackEngine);
    }

    @Override
    public int getPlaybackEngine() {
        return mPreferencesHelper.getPlaybackEngine();
    }

    @Override
    public void setFirstInSearchPorn91Video(boolean firstInSearchPorn91Video) {
        mPreferencesHelper.setFirstInSearchPorn91Video(firstInSearchPorn91Video);
    }

    @Override
    public boolean isFirstInSearchPorn91Video() {
        return mPreferencesHelper.isFirstInSearchPorn91Video();
    }

    @Override
    public void setDownloadVideoNeedWifi(boolean downloadVideoNeedWifi) {
        mPreferencesHelper.setDownloadVideoNeedWifi(downloadVideoNeedWifi);
    }

    @Override
    public boolean isDownloadVideoNeedWifi() {
        return mPreferencesHelper.isDownloadVideoNeedWifi();
    }

    @Override
    public void setOpenHttpProxy(boolean openHttpProxy) {
        mPreferencesHelper.setOpenHttpProxy(openHttpProxy);
    }

    @Override
    public boolean isOpenHttpProxy() {
        return mPreferencesHelper.isOpenHttpProxy();
    }

    @Override
    public void setOpenNightMode(boolean openNightMode) {
        mPreferencesHelper.setOpenNightMode(openNightMode);
    }

    @Override
    public boolean isOpenNightMode() {
        return mPreferencesHelper.isOpenNightMode();
    }

    @Override
    public void setProxyIpAddress(String proxyIpAddress) {
        mPreferencesHelper.setProxyIpAddress(proxyIpAddress);
    }

    @Override
    public String getProxyIpAddress() {
        return mPreferencesHelper.getProxyIpAddress();
    }

    @Override
    public void setProxyPort(int port) {
        mPreferencesHelper.setProxyPort(port);
    }

    @Override
    public int getProxyPort() {
        return mPreferencesHelper.getProxyPort();
    }

    @Override
    public void setNeverAskForWatchDownloadTip(boolean neverAskForWatchDownloadTip) {
        mPreferencesHelper.setNeverAskForWatchDownloadTip(neverAskForWatchDownloadTip);
    }

    @Override
    public boolean isNeverAskForWatchDownloadTip() {
        return mPreferencesHelper.isNeverAskForWatchDownloadTip();
    }

    @Override
    public void setIgnoreUpdateVersionCode(int versionCode) {
        mPreferencesHelper.setIgnoreUpdateVersionCode(versionCode);
    }

    @Override
    public int getIgnoreUpdateVersionCode() {
        return mPreferencesHelper.getIgnoreUpdateVersionCode();
    }

    @Override
    public void setForbiddenAutoReleaseMemory(boolean autoReleaseMemory) {
        mPreferencesHelper.setForbiddenAutoReleaseMemory(autoReleaseMemory);
    }

    @Override
    public boolean isForbiddenAutoReleaseMemory() {
        return mPreferencesHelper.isForbiddenAutoReleaseMemory();
    }

    @Override
    public void setNeedShowTipFirstViewForum9Content(boolean contentShowTip) {
        mPreferencesHelper.setNeedShowTipFirstViewForum9Content(contentShowTip);
    }

    @Override
    public boolean isNeedShowTipFirstViewForum9Content() {
        return mPreferencesHelper.isNeedShowTipFirstViewForum9Content();
    }

    @Override
    public void setNoticeVersionCode(int noticeVersionCode) {
        mPreferencesHelper.setNoticeVersionCode(noticeVersionCode);
    }

    @Override
    public int getNoticeVersionCode() {
        return mPreferencesHelper.getNoticeVersionCode();
    }

    @Override
    public void setMainFirstTabShow(int firstTabShow) {
        mPreferencesHelper.setMainFirstTabShow(firstTabShow);
    }

    @Override
    public int getMainFirstTabShow() {
        return mPreferencesHelper.getMainFirstTabShow();
    }

    @Override
    public void setMainSecondTabShow(int secondTabShow) {
        mPreferencesHelper.setMainSecondTabShow(secondTabShow);
    }

    @Override
    public int getMainSecondTabShow() {
        return mPreferencesHelper.getMainSecondTabShow();
    }

    @Override
    public void setSettingScrollViewScrollPosition(int position) {
        mPreferencesHelper.setSettingScrollViewScrollPosition(position);
    }

    @Override
    public int getSettingScrollViewScrollPosition() {
        return mPreferencesHelper.getSettingScrollViewScrollPosition();
    }

    @Override
    public void setOpenSkipPage(boolean openSkipPage) {
        mPreferencesHelper.setOpenSkipPage(openSkipPage);
    }

    @Override
    public boolean isOpenSkipPage() {
        return mPreferencesHelper.isOpenSkipPage();
    }

    @Override
    public void setCustomDownloadVideoDirPath(String customDirPath) {
        mPreferencesHelper.setCustomDownloadVideoDirPath(customDirPath);
    }

    @Override
    public String getCustomDownloadVideoDirPath() {
        return mPreferencesHelper.getCustomDownloadVideoDirPath();
    }

    @Override
    public void existProxyTest() {
        mApiHelper.existProxyTest();
    }

    @Override
    public Observable<Boolean> testPorn9VideoAddress() {
        return mApiHelper.testPorn9VideoAddress();
    }

    @Override
    public Observable<Boolean> testPorn9ForumAddress() {
        return mApiHelper.testPorn9ForumAddress();
    }

    @Override
    public Observable<Boolean> testPavAddress(String url) {
        return mApiHelper.testPavAddress(url);
    }

    @Override
    public String getVideoCacheProxyUrl(String originalVideoUrl) {
        return httpProxyCacheServer.getProxyUrl(originalVideoUrl, true);
    }
}
