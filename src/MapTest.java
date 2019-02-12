import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MapTest {

  private static final Set<String> SAP_VALUES = Sets.newHashSet("AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ",
    "AR", "AS", "AT", "AU", "AW", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BM", "BN", "BO", "BR", "BS",
    "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CS", "CU",
    "CV", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EF", "EG", "EH", "ER", "ES", "ET", "FI", "FJ",
    "FK", "FM", "FO", "FR", "GA", "GB", "GD", "GE", "GF", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU",
    "GW", "GY", "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IN", "IO", "IQ", "IR", "IS", "IT", "JM", "JO", "JP",
    "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU",
    "LV", "LY", "MA", "MC", "MD", "ME", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV",
    "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA", "PE", "PF",
    "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PT", "PW", "PY", "QA", "QU", "RE", "RO", "RS", "RU", "RW", "SA", "SB", "SC",
    "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SM", "SN", "SO", "SR", "ST", "SV", "SY", "SZ", "TC", "TD", "TF", "TG", "TH",
    "TJ", "TK", "TM", "TN", "TO", "TP", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE",
    "VG", "VI", "VN", "VU", "WF", "WS", "XC", "XK", "XL", "XS", "YE", "YT", "YU", "ZA", "ZM", "ZW");

  private static final Set<String> IDE_VALUES = Sets.newHashSet("DE", "LU", "CH", "AF", "EG", "AX", "AL", "DZ", "AS", "VI",
    "AD", "AO", "AI", "AG", "GQ", "AR", "AM", "AW", "AC", "AZ", "ET", "AU", "BS", "BH", "BD", "BB", "BE", "BZ", "BJ", "BM",
    "BQ", "BA", "BW", "BV", "BR", "VG", "BN", "BG", "BF", "BI", "EA", "CL", "CN", "CK", "CR", "CI", "CW", "DK", "DG", "DM",
    "DO", "DJ", "EC", "SV", "ER", "EE", "FK", "FO", "FJ", "FI", "FR", "GF", "PF", "GA", "GM", "GE", "GH", "GI", "GD", "GR",
    "GL", "GB", "GP", "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "HN", "HK", "IN", "ID", "IM", "IQ", "IR", "IE", "IS",
    "IL", "IT", "JM", "JP", "YE", "JE", "JO", "KY", "KH", "CM", "CA", "CV", "KZ", "QA", "KE", "KG", "KI", "CC", "CO", "KM",
    "CD", "CG", "KP", "KR", "XK", "HR", "CU", "KW", "LA", "LS", "LV", "LB", "LR", "LY", "LI", "LT", "MO", "MG", "MW", "MY",
    "MV", "ML", "MT", "MA", "MH", "MQ", "MR", "MU", "YT", "MK", "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MZ", "MM", "NA",
    "NR", "NP", "NC", "NZ", "NI", "NL", "NE", "NG", "NU", "MP", "NF", "NO", "OM", "AT", "TL", "PK", "PS", "PW", "PA", "PG",
    "PY", "PE", "PH", "PN", "PL", "PT", "PR", "RE", "RW", "RO", "RU", "SB", "BL", "MF", "ZM", "WS", "SM", "ST", "SA", "SE",
    "SN", "RS", "SC", "SL", "ZW", "SG", "SX", "SK", "SI", "SO", "ES", "LK", "SH", "KN", "LC", "PM", "VC", "ZA", "SD", "GS",
    "SR", "SJ", "SZ", "SY", "TJ", "TW", "TZ", "TH", "TG", "TK", "TO", "TT", "TA", "TD", "CZ", "TN", "TR", "TM", "TC",
    "TV", "UG", "UA", "HU", "UM", "UY", "US", "UZ", "VU", "VA", "VE", "AE", "VN", "WF", "CX", "BY", "EH", "CF", "CY", "BT", "BO");



  public static void main(String[] args) {

    List<String> missingInIde = new ArrayList<>();
    SAP_VALUES.forEach(sapValue -> {
      if (!IDE_VALUES.contains(sapValue)) {
        missingInIde.add(sapValue);
      } else {
        IDE_VALUES.remove(sapValue);
      }
    });
    missingInIde.forEach(value -> System.out.println("Value " + value + " is missing in IDE!"));
    IDE_VALUES.forEach(value -> System.out.println("IDE value " + value + " is not known in SAP:"));
  }
}
