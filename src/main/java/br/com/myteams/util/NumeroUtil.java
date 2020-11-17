/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myteams.util;

import br.com.myteams.exception.InfraestruturaException;

/**
 * @author welso
 */
public class NumeroUtil
{

    private NumeroUtil()
    {

    }

    public static int parseInt(String numero) throws InfraestruturaException
    {
        try
        {
            return Integer.parseInt(numero);
        }
        catch (NumberFormatException e)
        {
            throw new InfraestruturaException("Número invalido", e);
        }
    }

}
