
package com.paras.mostviewednews.data.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {

    private String type;
    private String caption;
    private String copyright;
    @SerializedName("media-metadata")
    @Expose
    private List<MediaMetadata> mediaMetadata = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<MediaMetadata> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<MediaMetadata> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

}
