package com.hoangsv.model;

public class ChampionFree {
    private String id,active,botEnabled,freeToPlay,botMmEnabled,rankedPlayEnabled,image;

    public ChampionFree() {
    }

    public ChampionFree(String id, String active, String botEnabled, String freeToPlay, String botMmEnabled, String rankedPlayEnabled,String image) {
        this.id = id;
        this.active = active;
        this.botEnabled = botEnabled;
        this.freeToPlay = freeToPlay;
        this.botMmEnabled = botMmEnabled;
        this.rankedPlayEnabled = rankedPlayEnabled;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getBotEnabled() {
        return botEnabled;
    }

    public void setBotEnabled(String botEnabled) {
        this.botEnabled = botEnabled;
    }

    public String getFreeToPlay() {
        return freeToPlay;
    }

    public void setFreeToPlay(String freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    public String getBotMmEnabled() {
        return botMmEnabled;
    }

    public void setBotMmEnabled(String botMmEnabled) {
        this.botMmEnabled = botMmEnabled;
    }

    public String getRankedPlayEnabled() {
        return rankedPlayEnabled;
    }

    public void setRankedPlayEnabled(String rankedPlayEnabled) {
        this.rankedPlayEnabled = rankedPlayEnabled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
