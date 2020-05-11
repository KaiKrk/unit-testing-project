package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CompteComptableTest {


    @Test
    public void getCompteComptableByNumero(){

        List<CompteComptable> compteComptableList = new ArrayList<>();
        for(int i = 1; i < 5; i++){
            CompteComptable compteComptable = Mockito.mock(CompteComptable.class);
            Mockito.when(compteComptable.getNumero()).thenReturn(i);
            Mockito.when(compteComptable.getLibelle()).thenReturn("Achat n° " + i);
            compteComptableList.add(compteComptable);
        }

        for(int i = 1; i < 5; i++)
            Assert.assertEquals(CompteComptable.getByNumero(compteComptableList, i).getLibelle(), "Achat n° " + i);
    }

    @Test
    public void toStringTest(){
        CompteComptable compteComptable = new CompteComptable();
        compteComptable.setLibelle("Achat");
        compteComptable.setNumero(5);
        Assert.assertEquals(compteComptable.toString(),"CompteComptable{numero=5, libelle='Achat'}");
    }
}
