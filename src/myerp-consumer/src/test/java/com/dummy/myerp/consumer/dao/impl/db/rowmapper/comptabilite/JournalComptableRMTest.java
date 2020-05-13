package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class JournalComptableRMTest {


    @Mock
    private ResultSet resultSet;

    @Test
    public void mapRow() throws SQLException {
        Mockito.when(resultSet.getString("code")).thenReturn("AC");
        Mockito.when(resultSet.getString("libelle")).thenReturn("Achat");

        JournalComptableRM journalComptableRM = new JournalComptableRM();
        JournalComptable resultJournalComptable = journalComptableRM.mapRow(resultSet, 0);

        Assert.assertEquals(resultJournalComptable.getCode(),"AC");
        Assert.assertEquals(resultJournalComptable.getLibelle(),"Achat");
    }
}
