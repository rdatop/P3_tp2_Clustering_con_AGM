package tests;

import static org.junit.Assert.*;
import modelo.Vertice;

import org.junit.Test;

public class VerticeTest {

	@Test
	public void comparaVerticesTest()
	{
		Vertice vert1=new Vertice(0,-34.52133782929332,-58.70068073272705);
		
		Vertice vert2=InstanciaVertice.getInstanciaCincoVertices().get(0);
		
		assertTrue(vert1.getId()==vert2.getId());
		
		assertEquals(vert1.getLatitud(),vert2.getLatitud(),1e-4 );
		
		assertEquals(vert1.getLongitud(),vert2.getLongitud(),1e-4 );
		
	}
	
	@Test
	public void comparaVerticesDistintosTest()
	{
		Vertice vert1=new Vertice(2,-34.52133788929356,-58.70068073279874);
		
		Vertice vert2=InstanciaVertice.getInstanciaCincoVertices().get(3);
		
		assertFalse(vert1.getId()==vert2.getId());
		
		assertNotEquals(vert1.getLatitud(),vert2.getLatitud(),1e-4 );
		
		assertNotEquals(vert1.getLongitud(),vert2.getLongitud(),1e-4 );
		
	}

}
