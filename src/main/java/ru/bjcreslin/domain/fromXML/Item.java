package ru.bjcreslin.domain.fromXML;

import lombok.Builder;
import lombok.Data;

/**
 * Класс  получаемый из XML для каждой закупки
 */
@Builder
@Data
public class Item {

    // <author>ФОНД "РЕГИОНАЛЬНЫЙ ФОНД КАПИТАЛЬНОГО РЕМОНТА МНОГОКВАРТИРНЫХ ДОМОВ ТОМСКОЙ ОБЛАСТИ"</author>
    private String author;
    // <link>/epz/order/notice/ea615/view/common-info.html?regNumber=206520000011900119</link>
    private String link;
    // <description>&lt;b&gt;Параметры поиска: &lt;/b&gt;&lt;br/&gt;&lt;strong&gt;Закупки по: &lt;/strong&gt; ПП РФ 615 (Капитальный ремонт)&lt;br/&gt;&lt;strong&gt;Этап закупки: &lt;/strong&gt;Подача заявок, Работа комиссии, Закупка завершена, Закупка отменена&lt;br/&gt;&lt;br&gt;&lt;strong&gt;Найденный результат:&lt;/strong&gt;&lt;br/&gt;&lt;strong&gt;Электронный аукцион на оказание услуг или выполнение работ по капитальному ремонту общего имущества в многоквартирном доме&lt;/strong&gt; &lt;a href='/epz/order/notice/ea615/view/common-info.html?regNumber=206520000011900119' target='_blank' &gt;&lt;strong&gt;№ &lt;/strong&gt;&lt;strong&gt;206520000011900119&lt;/strong&gt;&lt;/a&gt;&lt;br/&gt;&lt;strong&gt;Наименование объекта закупки: &lt;/strong&gt;Выполнение работ по капитальному ремонту общего имущества (капитальный ремонт крыши; капитальный ремонт и усиление несущих и ограждающих ненесущих конструкций, не отнесённых в соответствии с законодательством о градостроительной деятельности к реконструкции объектов капитального строительства (чердачное перекрытие)) в многоквартирном доме, расположенном по адресу: Томская область, г. Колпашево, ул. Обская, д. 75.&lt;br/&gt;&lt;strong&gt;Размещение выполняется по: &lt;/strong&gt;ПП РФ 615&lt;br/&gt;&lt;strong&gt;Наименование Заказчика: &lt;/strong&gt;ФОНД "РЕГИОНАЛЬНЫЙ ФОНД КАПИТАЛЬНОГО РЕМОНТА МНОГОКВАРТИРНЫХ ДОМОВ ТОМСКОЙ ОБЛАСТИ"&lt;br/&gt;&lt;strong&gt;Начальная цена контракта: &lt;/strong&gt;3636024.43&lt;strong&gt; Валюта: &lt;/strong&gt;Российский рубль&lt;br/&gt;&lt;strong&gt;Размещено: &lt;/strong&gt;10.06.2019&lt;br/&gt;&lt;strong&gt;Обновлено: &lt;/strong&gt;04.07.2019&lt;br/&gt;&lt;strong&gt;Этап размещения: &lt;/strong&gt;Закупка завершена&lt;br/&gt;&lt;strong&gt;Идентификационный код закупки (ИКЗ): &lt;/strong&gt;&lt;br/&gt;</description>
    private String description;
    //   <title>Электронный аукцион на оказание услуг или выполнение работ по капитальному ремонту общего имущества в многоквартирном доме №206520000011900119</title>
    private String title;
    // <pubDate>Mon, 10 Jun 2019 13:32:20 GMT</pubDate>
    private String pubDate;

    @Override
    public String toString() {
        return "ClassPojo [author = " + author + ", link = " + link + ", description = " + description + ", title = " + title + ", pubDate = " + pubDate + "]";
    }
}
