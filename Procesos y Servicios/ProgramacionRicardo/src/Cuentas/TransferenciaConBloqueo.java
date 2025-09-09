package Cuentas;

public class TransferenciaConBloqueo {
	public static void main(String[] args) {

		Cuenta c1 = new Cuenta(12500, 1L);
		System.out.printf("Saldo inicial de %s: %d\n", c1.getNumerocuenta(), c1.getSaldo());

		Cuenta c2 = new Cuenta(23400,2L);
		System.out.printf("Saldo inicial de %s: %d\n", c2.getNumerocuenta(), c2.getSaldo());

		Thread h1 = new Thread(new Hilo(c1, c2, "c1 --> c2"));
		Thread h2 = new Thread(new Hilo(c2, c1, "c2 --> c1"));

		h1.start();
		h2.start();

		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("***********************************************************");
		System.out.printf("Saldo Final de %s es %d\n", c1.getNumerocuenta(), c1.getSaldo());
		System.out.printf("Saldo Final de %s es %d\n", c2.getNumerocuenta(), c2.getSaldo());
	}
}
