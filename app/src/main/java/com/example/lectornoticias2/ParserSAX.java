package com.example.lectornoticias2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParserSAX
{

    private URL url;

    private InputStream is;

    public ArrayList<Item> parsear(String urlFuente)
    {
        try
        {
            url = new URL(urlFuente);
        }catch (MalformedURLException mal_ex)
        {
            throw new RuntimeException(mal_ex);
        }


        SAXParserFactory fabrica = SAXParserFactory.newInstance();

        try
        {
            is = url.openConnection().getInputStream();
        }catch (IOException iex)
        {
            throw new RuntimeException(iex);
        }

        try
        {
            SAXParser parser = fabrica.newSAXParser();
            ManejadorSAX manejador = new ManejadorSAX();
            parser.parse(is, manejador);
            return manejador.getItems();
        }catch (Exception exc)
        {
            throw new RuntimeException(exc);
        }

    }


}
