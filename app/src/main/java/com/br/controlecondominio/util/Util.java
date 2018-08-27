package com.br.controlecondominio.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Util {

    public static String formatarValorParaReal(Double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formato.format(valor);
    }
}
