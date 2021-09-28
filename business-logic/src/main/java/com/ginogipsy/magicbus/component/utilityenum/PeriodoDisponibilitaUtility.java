package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.customexception.notfound.PeriodoDisponibilitaNotFoundException;

public interface PeriodoDisponibilitaUtility {

    String verifyPeriodoDisponibilita(String periodoDisponibilita) throws PeriodoDisponibilitaNotFoundException;
}
