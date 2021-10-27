package com.my.xunboplayerlib.CustomJzvd;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;

import com.my.xunboplayerlib.JZMediaInterface;
import com.my.xunboplayerlib.Jzvd;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;

/**
 *
 * Created by Nathen on 2017/11/18.
 */

public class  JZMediaIjk extends JZMediaInterface implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnTimedTextListener {
    IjkMediaPlayer ijkMediaPlayer;

    public JZMediaIjk(Jzvd jzvd) {
        super(jzvd);
    }

    @Override
    public void start() {
        if (ijkMediaPlayer != null) ijkMediaPlayer.start();
    }

    @Override
    public void prepare() {

        release();
        mMediaHandlerThread = new HandlerThread("JZVD");
        mMediaHandlerThread.start();
        mMediaHandler = new Handler(mMediaHandlerThread.getLooper());//主线程还是非主线程，就在这里
        handler = new Handler();

        mMediaHandler.post(() -> {

            ijkMediaPlayer = new IjkMediaPlayer();
            if(ijkMediaPlayer!=null){
                if(ijkMediaPlayer!=null){
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-buffer-size", 1024 * 1024);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnPreparedListener(JZMediaIjk.this);
                }

                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnVideoSizeChangedListener(JZMediaIjk.this);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnCompletionListener(JZMediaIjk.this);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnErrorListener(JZMediaIjk.this);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnInfoListener(JZMediaIjk.this);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnBufferingUpdateListener(JZMediaIjk.this);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnSeekCompleteListener(JZMediaIjk.this);
                }
                if(ijkMediaPlayer!=null){

                    ijkMediaPlayer.setOnTimedTextListener(JZMediaIjk.this);
                }

                try {
                    if(ijkMediaPlayer!=null){
                        ijkMediaPlayer.setDataSource(jzvd.jzDataSource.getCurrentUrl()==null?"":jzvd.jzDataSource.getCurrentUrl().toString());
                    }
                    if(ijkMediaPlayer!=null){

                        ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    }
                    if(ijkMediaPlayer!=null){

                        ijkMediaPlayer.setScreenOnWhilePlaying(true);
                    }
                    if(ijkMediaPlayer!=null){
                        ijkMediaPlayer.prepareAsync();
                    }
                    SurfaceTexture surfaceTexture = jzvd.textureView.getSurfaceTexture();
                    if (surfaceTexture==null){
                        return;
                    }
                    Surface surface = new Surface(surfaceTexture);
                    if (surface ==null){
                        return;
                    }
                    if(ijkMediaPlayer!=null){
                        ijkMediaPlayer.setSurface(surface);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    @Override
    public void pause() {
        ijkMediaPlayer.pause();
    }

    @Override
    public boolean isPlaying() {
        return ijkMediaPlayer.isPlaying();
    }

    @Override
    public void seekTo(long time) {
        ijkMediaPlayer.seekTo(time);
    }

    @Override
    public void release() {
        if (mMediaHandler != null && mMediaHandlerThread != null && ijkMediaPlayer != null) {//不知道有没有妖孽
            HandlerThread tmpHandlerThread = mMediaHandlerThread;
            IjkMediaPlayer tmpMediaPlayer = ijkMediaPlayer;
            JZMediaInterface.SAVED_SURFACE = null;

            mMediaHandler.post(() -> {
                tmpMediaPlayer.setSurface(null);
                tmpMediaPlayer.release();
                tmpHandlerThread.quit();
            });
            ijkMediaPlayer = null;
        }
    }

    @Override
    public long getCurrentPosition() {
        if (ijkMediaPlayer==null){
            return 0;
        }
        return ijkMediaPlayer.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        if (ijkMediaPlayer==null){
            return 0;
        }
        return ijkMediaPlayer.getDuration();
    }

    @Override
    public void setVolume(float leftVolume, float rightVolume) {
        ijkMediaPlayer.setVolume(leftVolume, rightVolume);
    }

    @Override
    public void setSpeed(float speed) {
        ijkMediaPlayer.setSpeed(speed);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        handler.post(() -> jzvd.onPrepared());
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
        handler.post(() -> jzvd.onVideoSizeChanged(iMediaPlayer.getVideoWidth(), iMediaPlayer.getVideoHeight()));
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, final int what, final int extra) {
        handler.post(() -> jzvd.onError(what, extra));
        return true;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, final int what, final int extra) {
        handler.post(() -> jzvd.onInfo(what, extra));
        return false;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, final int percent) {
        handler.post(() -> jzvd.setBufferProgress(percent));
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
        handler.post(() -> jzvd.onSeekComplete());
    }

    @Override
    public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {

    }

    @Override
    public void setSurface(Surface surface) {
        ijkMediaPlayer.setSurface(surface);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (SAVED_SURFACE == null) {
            SAVED_SURFACE = surface;
            prepare();
        } else {
            jzvd.textureView.setSurfaceTexture(SAVED_SURFACE);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        handler.post(() -> jzvd.onAutoCompletion());
    }
}
