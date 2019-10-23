package com.sexycode.simplerpc.comsumer.service;

import com.sexycode.simplerpc.provider.service.Calculator;
import com.sexycode.simplerpc.reuqest.CalculateRpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class MyCalculatorImpl implements Calculator {
    final  String  address = "127.0.0.1";
    private  static Logger log  = LoggerFactory.getLogger(MyCalculatorImpl.class);
    @Override
    public int add(int a, int b) {
        try
        {
            CalculateRpcRequest calculateRpcRequest = new CalculateRpcRequest();
            calculateRpcRequest.setA(a);
            calculateRpcRequest.setB(b);
            calculateRpcRequest.setMethod("add");
            Socket socket = new Socket(address,9090);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(calculateRpcRequest);
            ObjectInputStream in  = new ObjectInputStream(socket.getInputStream());
            Object  obj = in.readObject();
            if(obj instanceof  Integer)
            {
                return (Integer)obj;
            }
            else
                throw new InternalError();
        }
        catch (Exception e
        )
        {
            log.error("fail",e);
        }
        return -1;
    }
}
