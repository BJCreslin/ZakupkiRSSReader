package ru.bjcreslin.domain.fromXML;

/**
 * Из XML файла обёртка над channel
 */
public class Rss {

    private Channel channel;
    //<rss version="2.0">
    private String version;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ClassPojo [channel = " + channel + ", version = " + version + "]";
    }
}
