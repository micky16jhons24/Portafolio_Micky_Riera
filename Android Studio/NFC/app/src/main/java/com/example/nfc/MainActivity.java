package com.example.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private NfcAdapter mNfcAdapter;
    private TextView mTextView;
    private PendingIntent pendingIntent;
    private Tag mTag;  // Variable para almacenar la etiqueta NFC detectada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            mTextView.setText("NFC no soportado en este dispositivo.");
            return;
        }

        // Crear un PendingIntent para capturar eventos NFC
        pendingIntent = PendingIntent.getActivity(
                this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
        );

        // Botón para activar la escritura en la etiqueta NFC
        Button writeButton = findViewById(R.id.writeButton);
        writeButton.setOnClickListener(v -> {
            if (mTag != null) {
                String messageToWrite = "Mensaje desde este dispositivo micky";
                writeNfcMessage(mTag, messageToWrite);  // Escribimos en la etiqueta
            } else {
                Toast.makeText(this, "No se detectó ninguna etiqueta NFC.", Toast.LENGTH_SHORT).show();
            }
        });

        // Ya no necesitas setNdefPushMessageCallback, ya que esa función ha sido descontinuada
        // El código aquí es suficiente para manejar la escritura y lectura de mensajes NFC.
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }

        // Verificar si hay datos NFC disponibles
        Intent intent = getIntent();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            processNfcIntent(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processNfcIntent(intent);
    }

    private void processNfcIntent(Intent intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            NdefMessage[] messages = getNdefMessages(intent);
            if (messages != null && messages.length > 0) {
                String message = new String(messages[0].getRecords()[0].getPayload(), StandardCharsets.UTF_8);
                mTextView.setText("Mensaje NFC recibido: " + message);
            }
            // Guardamos la etiqueta NFC detectada
            mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    private NdefMessage[] getNdefMessages(Intent intent) {
        NdefMessage[] messages = null;
        if (intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES) != null) {
            messages = new NdefMessage[intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES).length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = (NdefMessage) intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)[i];
            }
        }
        return messages;
    }

    // Método para escribir el mensaje en la etiqueta NFC
    public void writeNfcMessage(Tag tag, String message) {
        if (tag == null) {
            Toast.makeText(this, "No se detectó una etiqueta NFC", Toast.LENGTH_SHORT).show();
            return;
        }

        NdefRecord record = NdefRecord.createTextRecord("en", message);
        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{record});

        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (ndef.isWritable()) {
                    ndef.writeNdefMessage(ndefMessage);
                    Toast.makeText(this, "Mensaje escrito en la etiqueta NFC", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "La etiqueta NFC no es escribible", Toast.LENGTH_SHORT).show();
                }
                ndef.close();
            }
        } catch (IOException | android.nfc.FormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al escribir en la etiqueta NFC", Toast.LENGTH_SHORT).show();
        }
    }
}
