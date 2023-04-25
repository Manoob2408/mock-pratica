package valueprojects.mock_na_pratica;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.value.jogo.builder.CriadorDeJogo;
import dominio.Jogo;
import dominio.Participante;
import dominio.Sms;
import dominio.Vencedor;
import infra.JogoDao;
import infra.JogoDaoFalso;
import service.FinalizaJogo;
import static org.mockito.Mockito.*;

public class FinalizaJogoTest {
	
	 @Test
	    public void deveFinalizarJogosDaSemanaAnterior() {

	        Calendar antiga = Calendar.getInstance();
	        antiga.set(1999, 1, 20);

	        Jogo jogo1 = new CriadorDeJogo().para("Caça moedas")
	            .naData(antiga).constroi();
	        Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras")
	            .naData(antiga).constroi();

	        // mock no lugar de dao falso
	        
	        List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

	        JogoDao daoFalso = mock(JogoDao.class);

	        when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

	        FinalizaJogo finalizador = new FinalizaJogo(daoFalso);
	        finalizador.finaliza();

	        assertTrue(jogo1.isFinalizado());
	        assertTrue(jogo2.isFinalizado());
	        assertEquals(2, finalizador.getTotalFinalizados());
	    }
	 
	 @Test
		public void deveVerificarSeMetodoAtualizaFoiInvocado() {

			Calendar antiga = Calendar.getInstance();
			antiga.set(1999, 1, 20);

			Jogo jogo1 = new CriadorDeJogo().para("Cata moedas").naData(antiga).constroi();
			Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras").naData(antiga).constroi();

			// mock no lugar de dao falso

			List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

			JogoDao daoFalso = mock(JogoDao.class);

			when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

			FinalizaJogo finalizador = new FinalizaJogo(daoFalso);
			finalizador.finaliza();

			verify(daoFalso, times(1)).atualiza(jogo1);
			//Mockito.verifyNoInteractions(daoFalso);				
			
		}
	 
	 @Test
	 public void deveIdentificarVencedor() {
		 Participante leonardo = new Participante("Leonardo");
		 Participante manu = new Participante("Manu");
		 Participante rafa = new Participante("Rafa");
		 Vencedor vencedor = new Vencedor();
		 JogoDaoFalso jogoDao = new JogoDaoFalso();
		 Sms sms = new Sms("Você foi o vencedor!");
		 
		 boolean isFinalizado = false;
		 
		 Calendar antiga = Calendar.getInstance();
	        antiga.set(2023, 4, 17);

	        Jogo jogo1 = new CriadorDeJogo().para("Jogo1")
	            .naData(antiga).constroi();
	        
	    jogo1.finaliza();
	    isFinalizado = jogo1.isFinalizado();
	    vencedor.salvar(rafa);
	    rafa.addSms(sms);
	    jogoDao.salva(jogo1);
	    
	    assertTrue(jogo1.isFinalizado());
	    assertTrue(rafa.getSms().contains(sms));
		 
	 }
	 
	 
		 
	}

 

	
	

	
