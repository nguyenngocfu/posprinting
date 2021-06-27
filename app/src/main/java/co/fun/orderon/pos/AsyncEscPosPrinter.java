package co.fun.orderon.pos;

import com.dantsu.escposprinter.EscPosPrinterSize;
import com.dantsu.escposprinter.connection.DeviceConnection;

public class AsyncEscPosPrinter extends EscPosPrinterSize {
    private DeviceConnection printerConnection;
    private String textToPrint = "";
    private String language = "2";

    public AsyncEscPosPrinter(DeviceConnection printerConnection, int printerDpi, float printerWidthMM, int printerNbrCharactersPerLine, String language) {
        super(printerDpi, printerWidthMM, printerNbrCharactersPerLine);
        this.printerConnection = printerConnection;
        this.language = language;
    }

    public DeviceConnection getPrinterConnection() {
        return this.printerConnection;
    }

    public AsyncEscPosPrinter setTextToPrint(String textToPrint) {
        this.textToPrint = textToPrint;
        return this;
    }

    public String getTextToPrint() {
        return this.textToPrint;
    }

    public String getLanguage() {
        return language;
    }
}