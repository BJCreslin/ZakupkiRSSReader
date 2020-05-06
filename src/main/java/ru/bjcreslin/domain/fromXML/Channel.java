package ru.bjcreslin.domain.fromXML;

/**
 * Результаты из xml файла. Это обёртка для коллекции Item
 */
public class Channel {
    //
    private Item[] item;
//  <link>/epz/order/extendedsearch/results.html?morphology=on&amp;search-filter=%D0%94%D0%B0%D1%82%D0%B5+%D1%80%D0%B0%D0%B7%D0%BC%D0%B5%D1%89%D0%B5%D0%BD%D0%B8%D1%8F&amp;pageNumber=1&amp;sortDirection=false&amp;recordsPerPage=_50&amp;showLotsInfoHidden=false&amp;sortBy=UPDATE_DATE&amp;ppRf615=on&amp;af=on&amp;ca=on&amp;pc=on&amp;pa=on&amp;currencyIdGeneral=-1&amp;delKladrIds=5277393&amp;delKladrIdsCodes=70000000000&amp;OrderPlacementSmallBusinessSubject=on&amp;OrderPlacementRnpData=on&amp;OrderPlacementExecutionRequirement=on&amp;orderPlacement94_0=0&amp;orderPlacement94_1=0&amp;orderPlacement94_2=0</link>
    private String link;
//  <description>Результаты поиска в реестре заказов и закупок</description>
    private String description;
//   <title>Результаты поиска в реестре заказов и закупок</title>
    private String title;

    public Item[] getItem() {
        return item;
    }

    public void setItem(Item[] item) {
        this.item = item;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ClassPojo [item = " + item + ", link = " + link + ", description = " + description + ", title = " + title + "]";
    }
}
