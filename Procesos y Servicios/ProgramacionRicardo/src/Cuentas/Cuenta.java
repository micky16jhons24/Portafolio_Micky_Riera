package Cuentas;

import java.util.concurrent.atomic.AtomicLong;

public class Cuenta {
 int saldo;
// final String numCuenta;
 static AtomicLong numerocuenta = new AtomicLong(0);
 
 public Cuenta(  int saldo,AtomicLong numerocuenta ) {
	 this.saldo = saldo;
	 this.numerocuenta=numerocuenta;
//	 this.numCuenta= numCuenta;
 }

 public static AtomicLong getNumerocuenta() {
	return numerocuenta;
}

public static void setNumerocuenta(AtomicLong numerocuenta) {
	Cuenta.numerocuenta = numerocuenta;
}

public void setSaldo(int saldo) {
	this.saldo = saldo;
}

public synchronized int getSaldo() {
	 return this.saldo;
 }
 
 public synchronized void ingresar(int cantidad) {
	 this.saldo += cantidad;
 }
 
 public synchronized void retirar(int cantidad) {
	 this.saldo -= cantidad;
 }


 
}
