package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test 
    public void liikaaOttaminenEiOnnistu() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(10);
        
        //Pitäisi antaa kaikki mitä löytyy, ei luoda tyhjästä lisää, eli tässä 8
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void liikaaLaittaminenEiOnnistu() {
        varasto.lisaaVarastoon(321321);
        
        //pitäisi olla yhtäkuin maksimisaldo
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusLuoKayttokelvottomanVaraston() {
        Varasto testiVarasto = new Varasto(-10);
        
        assertEquals(0, testiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
    @Test
    public void varastonLuontiAlkusaldollaToimii() {
        Varasto testi = new Varasto(10, 5);
        
        assertEquals(5, testi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiNegatiivisellaTilavuudellaSaldonKanssa() {
        Varasto testi = new Varasto(-10, 5);
        
        assertEquals(0.0, testi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiNegatiivisellaTilavuudellaSaldonKanssaEiLisaaSaldoa() {
        Varasto testi = new Varasto(-10, 5);
        
        assertEquals(testi.getSaldo(), testi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiNegatiivisellaSaldollaAntaaNolla() {
        Varasto testi = new Varasto(10, -5);
        
        assertEquals(0.0, testi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysLopettaaSuorituksen() {
        double alkuarvo = varasto.getSaldo();
        varasto.lisaaVarastoon(-10);
        
        assertEquals(alkuarvo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoLopettaaSuorituksen() {
        double alkuarvo = varasto.getSaldo();
        varasto.otaVarastosta(-10);
        
        assertEquals(alkuarvo, varasto.getSaldo(), vertailuTarkkuus);
    }

}