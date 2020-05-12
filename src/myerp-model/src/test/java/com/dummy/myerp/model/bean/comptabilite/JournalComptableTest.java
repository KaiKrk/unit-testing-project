package com.dummy.myerp.model.bean.comptabilite;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JournalComptableTest {
    @Test
    public void getJournalComptableByNumero(){

        List<JournalComptable> journalComptableList = new ArrayList<>();

        for(int i = 1; i < 5; i++){
            JournalComptable journalComptable = Mockito.mock(JournalComptable.class);
            Mockito.when(journalComptable.getCode()).thenReturn("AC" + i);
            Mockito.when(journalComptable.getLibelle()).thenReturn("Achat " + i);
            journalComptableList.add(journalComptable);
        }

        for(int i = 1; i < 5; i++)
            Assert.assertEquals(JournalComptable.getByCode(journalComptableList, "AC"+i).getLibelle(), "Achat " + i);

    }

    @Test
    public void toStringTest(){
        JournalComptable journalComptable =  new JournalComptable("AC","Achat");
        journalComptable.toString();
        System.out.println(journalComptable.toString());
        Assert.assertEquals(journalComptable.toString(),"JournalComptable{code='AC', libelle='Achat'}");
    }
}
