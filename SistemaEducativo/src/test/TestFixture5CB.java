package test;

import sistemaeducativo.Facultad;

public class TestFixture5CB
{
    Facultad facultad;
    public TestFixture5CB()
    {
    }

    public void setUp()
    {
        this.facultad=new Facultad(); 
        
    }

    public void tearDown()
    {
    }
}
