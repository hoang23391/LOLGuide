package com.hoangsv.model;

/**
 * Created by User on 2/20/2017.
 */

public class Champion {
    private String id  , name , key , title , position , video , prp , pip , created , spells , ferocity , resolve ,
            cunning , rune , skill , item , weak_against , strong_against , lore , tags , info , spells2 , blurb ,
            stats , passive , ver , image , skins , allytips , enemytips , partype, recommended;

    public Champion() {
    }

    public Champion(String id, String name, String key, String title, String position, String video, String prp, String pip, String created, String spells, String ferocity, String resolve, String cunning, String rune, String skill, String item, String weak_against, String strong_against, String lore, String tags, String info, String spells2, String blurb, String stats, String passive, String ver, String image, String skins, String allytips, String enemytips, String partype, String recommended) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.title = title;
        this.position = position;
        this.video = video;
        this.prp = prp;
        this.pip = pip;
        this.created = created;
        this.spells = spells;
        this.ferocity = ferocity;
        this.resolve = resolve;
        this.cunning = cunning;
        this.rune = rune;
        this.skill = skill;
        this.item = item;
        this.weak_against = weak_against;
        this.strong_against = strong_against;
        this.lore = lore;
        this.tags = tags;
        this.info = info;
        this.spells2 = spells2;
        this.blurb = blurb;
        this.stats = stats;
        this.passive = passive;
        this.ver = ver;
        this.image = image;
        this.skins = skins;
        this.allytips = allytips;
        this.enemytips = enemytips;
        this.partype = partype;
        this.recommended = recommended;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPrp() {
        return prp;
    }

    public void setPrp(String prp) {
        this.prp = prp;
    }

    public String getPip() {
        return pip;
    }

    public void setPip(String pip) {
        this.pip = pip;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getSpells() {
        return spells;
    }

    public void setSpells(String spells) {
        this.spells = spells;
    }

    public String getFerocity() {
        return ferocity;
    }

    public void setFerocity(String ferocity) {
        this.ferocity = ferocity;
    }

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve;
    }

    public String getCunning() {
        return cunning;
    }

    public void setCunning(String cunning) {
        this.cunning = cunning;
    }

    public String getRune() {
        return rune;
    }

    public void setRune(String rune) {
        this.rune = rune;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getWeak_against() {
        return weak_against;
    }

    public void setWeak_against(String weak_against) {
        this.weak_against = weak_against;
    }

    public String getStrong_against() {
        return strong_against;
    }

    public void setStrong_against(String strong_against) {
        this.strong_against = strong_against;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSpells2() {
        return spells2;
    }

    public void setSpells2(String spells2) {
        this.spells2 = spells2;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getPassive() {
        return passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSkins() {
        return skins;
    }

    public void setSkins(String skins) {
        this.skins = skins;
    }

    public String getAllytips() {
        return allytips;
    }

    public void setAllytips(String allytips) {
        this.allytips = allytips;
    }

    public String getEnemytips() {
        return enemytips;
    }

    public void setEnemytips(String enemytips) {
        this.enemytips = enemytips;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }
}
