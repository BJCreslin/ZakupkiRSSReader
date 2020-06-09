package ru.bjcreslin.domain.fromHtml;

class RepaireTradeProcedureParserTest {

    @org.junit.jupiter.api.Test
    void getResult() {
        String uin = "206520000012000082";
        RepaireTradeProcedureParser repaireTradeProcedureParser = new RepaireTradeProcedureParser();

        var res = repaireTradeProcedureParser.getResult(uin);
        System.out.println(res);

    }
}
