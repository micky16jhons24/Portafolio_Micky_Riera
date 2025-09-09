package Cuentas;

class Hilo implements Runnable {
	Cuenta c1, c2;
	String nomHilo;

	Hilo(Cuenta c1, Cuenta c2, String nomHilo) {
		this.c1 = c1;
		this.c2 = c2;
		this.nomHilo = nomHilo;
	}

	public String getNomHilo() {
		return nomHilo;
	}

	@Override
	public void run() {
		int cantidad = 10;
		int numTransf = 0;
		for (int i = 0; i < 10000; ++i) {
			
			if(c1.getNumerocuenta()==c2.getNumerocuenta() > 0) {
				
				synchronized (c1) {
				synchronized (c2) {
					if (c1.getSaldo() >= cantidad) {
						c1.retirar(cantidad);
						c2.ingresar(cantidad);
					}
				}
			}
			++numTransf;
			System.out.printf("*** Haciendo %s, %d transferencias hechas de %s a %s\n", this.getNomHilo(), numTransf,
					c1.getNumerocuenta(), c2.getNumerocuenta());
		}
				
			}
			
	}
}
