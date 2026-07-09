package com.onevcat.uniwebview;

import android.media.MediaPlayer;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.VideoView;

/* JADX INFO: loaded from: classes.dex */
public class VideoEnabledWebChromeClient extends WebChromeClient implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private View activityNonVideoView;
    private ViewGroup activityVideoView;
    private boolean isVideoFullscreen;
    private View loadingView;
    private ToggledFullscreenCallback toggledFullscreenCallback;
    private WebChromeClient.CustomViewCallback videoViewCallback;
    private FrameLayout videoViewContainer;
    private VideoEnabledWebView webView;

    public interface ToggledFullscreenCallback {
        void toggledFullscreen(boolean z);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    public VideoEnabledWebChromeClient() {
    }

    public VideoEnabledWebChromeClient(View view, ViewGroup viewGroup) {
        this.activityNonVideoView = view;
        this.activityVideoView = viewGroup;
        this.loadingView = null;
        this.webView = null;
        this.isVideoFullscreen = false;
    }

    public VideoEnabledWebChromeClient(View view, ViewGroup viewGroup, View view2) {
        this.activityNonVideoView = view;
        this.activityVideoView = viewGroup;
        this.loadingView = view2;
        this.webView = null;
        this.isVideoFullscreen = false;
    }

    public VideoEnabledWebChromeClient(View view, ViewGroup viewGroup, View view2, VideoEnabledWebView videoEnabledWebView) {
        this.activityNonVideoView = view;
        this.activityVideoView = viewGroup;
        this.loadingView = view2;
        this.webView = videoEnabledWebView;
        this.isVideoFullscreen = false;
    }

    public boolean isVideoFullscreen() {
        return this.isVideoFullscreen;
    }

    public void setOnToggledFullscreen(ToggledFullscreenCallback toggledFullscreenCallback) {
        this.toggledFullscreenCallback = toggledFullscreenCallback;
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        Logger.getInstance().verbose("onShowCustomView!!!");
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            View focusedChild = frameLayout.getFocusedChild();
            this.isVideoFullscreen = true;
            this.videoViewContainer = frameLayout;
            this.videoViewCallback = customViewCallback;
            this.activityNonVideoView.setVisibility(4);
            this.activityVideoView.addView(this.videoViewContainer, new ViewGroup.LayoutParams(-1, -1));
            this.activityVideoView.setVisibility(0);
            if (focusedChild instanceof VideoView) {
                VideoView videoView = (VideoView) focusedChild;
                videoView.setOnPreparedListener(this);
                videoView.setOnCompletionListener(this);
                videoView.setOnErrorListener(this);
            } else {
                VideoEnabledWebView videoEnabledWebView = this.webView;
                if (videoEnabledWebView != null && videoEnabledWebView.getSettings().getJavaScriptEnabled() && (focusedChild instanceof SurfaceView)) {
                    this.webView.loadUrl((((((((("javascript:var _ytrp_html5_video_last;") + "var _ytrp_html5_video = document.getElementsByTagName('video')[0];") + "if (_ytrp_html5_video != undefined && _ytrp_html5_video != _ytrp_html5_video_last) {") + "_ytrp_html5_video_last = _ytrp_html5_video;") + "function _ytrp_html5_video_ended() {") + "window.location.href = 'uniwebviewinternal://__uniwebview_internal_video_end';") + "}") + "_ytrp_html5_video.addEventListener('ended', _ytrp_html5_video_ended);") + "}");
                }
            }
            ToggledFullscreenCallback toggledFullscreenCallback = this.toggledFullscreenCallback;
            if (toggledFullscreenCallback != null) {
                toggledFullscreenCallback.toggledFullscreen(true);
            }
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        onShowCustomView(view, customViewCallback);
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        if (this.isVideoFullscreen) {
            this.activityVideoView.setVisibility(4);
            this.activityVideoView.removeView(this.videoViewContainer);
            this.activityNonVideoView.setVisibility(0);
            WebChromeClient.CustomViewCallback customViewCallback = this.videoViewCallback;
            if (customViewCallback != null && !customViewCallback.getClass().getName().contains(".chromium.")) {
                this.videoViewCallback.onCustomViewHidden();
            }
            this.isVideoFullscreen = false;
            this.videoViewContainer = null;
            this.videoViewCallback = null;
            ToggledFullscreenCallback toggledFullscreenCallback = this.toggledFullscreenCallback;
            if (toggledFullscreenCallback != null) {
                toggledFullscreenCallback.toggledFullscreen(false);
            }
        }
        Logger.getInstance().verbose("Clear Focus");
        this.webView.clearFocus();
    }

    @Override // android.webkit.WebChromeClient
    public View getVideoLoadingProgressView() {
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(0);
            return this.loadingView;
        }
        return super.getVideoLoadingProgressView();
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        onHideCustomView();
    }

    public boolean onBackPressed() {
        if (!this.isVideoFullscreen) {
            return false;
        }
        onHideCustomView();
        return true;
    }
}
