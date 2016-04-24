package ua.org.gostroy.domain.entity.notpersist;

/**
 * Created by Sergey on 4/24/2016.
 */
public enum IataAirportsCode {

    //take indormation from here https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat
    CPH("CPH", "Kastrup/Copenhagen"),
    STP("STP", "London St Pancras"),
    STN("STN", "London Stansted"),
    VSG("VSG", "Luhansk International Airport"),
    PDO("PDO", "Pendopo Airport"),
    BOD("BOD", "Merignac"),
    DIJ("DIJ", "Longvic"),
    BEV("BEV", "Teyman"),
    VER("VER", "General Heriberto Jara Intl"),
    UND("UND", "Konduz"),
    ISG("ISG", "Ishigaki"),
    CXJ("CXJ", "Campo Dos Bugres"),
    AXM("AXM", "El Eden"),
    BHO("BHO", "Bhopal"),
    MES("MES", "Polonia"),
    OME("OME", "Nome"),
    TRK("TRK", "Juwata"),
    HKV("HKV", "Ercan International Airport"),
    YHC("YHC", "Vancouver Harbour Water Airport"),
    YGO("YGO", "Gods Lake Narrows Airport"),;

    public String iataCode;
    public String airportName;

    IataAirportsCode(String iataCode, String airportName) {
        this.iataCode = iataCode;
        this.airportName = airportName;
    }

    public static IataAirportsCode getByIataCode(String iataCode) {
        IataAirportsCode iataAirportsCode = null;
        for (IataAirportsCode item : IataAirportsCode.values()) {
            if ("iataCode".equals(item.iataCode)) {
                iataAirportsCode = item;
                break;
            }
        }

        return iataAirportsCode;
    }
}
