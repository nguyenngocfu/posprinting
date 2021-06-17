Charset encoding
To change charset encoding of the printer, use EscPosCharsetEncoding class :

EscPosPrinter printer = new EscPosPrinter(deviceConnection, 203, 48f, 32, new EscPosCharsetEncoding("windows-1252", 16));
escPosCharsetId may change with printer model. Follow this link to find escPosCharsetId that works with many printers

Formatted text : syntax guide
New line
Use \n to create a new line of text.

Text alignment and column separation
Add an alignment tag on a same line of text implicitly create a new column.

Column alignment tags :

[L] : left side alignment
[C] : center alignment
[R] : right side alignment
Example :

[L]Some text : One column aligned to left
[C]Some text : One column aligned to center
[R]Some text : One column aligned to right
[L]Some text[L]Some other text : Two columns aligned to left. Some other text starts in the center of the paper.
[L]Some text[R]Some other text : Two columns, first aligned to left, second aligned to right. Some other text is printed at the right of paper.
[L]Some[R]text[R]here : Three columns.
[L][R]text[R]here : Three columns. The first is empty but it takes a third of the available space.
Font
Size
<font></font> tag allows you to change the font size and color. Default size is normal / black.

<font size='normal'>Some text</font> : Normal size

<font size='wide'>Some text</font> : Double width of medium size

<font size='tall'>Some text</font> : Double height of medium size

<font size='big'>Some text</font> : Double width and height of medium size

<font color='black'>Some text</font> : black text - white background

<font color='bg-black'>Some text</font> : white text - black background

<font color='red'>Some text</font> : red text - white background (Not working on all printer)

<font color='bg-red'>Some text</font> : white text - red background (Not working on all printer)

Bold
<b></b> tag allows you to change the font weight.

<b>Some text</b>
Underline
<u></u> tag allows you to underline the text.

<u>Some text</u> text underlined
<u type='double'>Some text</u> text double-strike (Not working on all printer)
Image
<img></img> tag allows you to print image. Inside the tag you need to write a hexadecimal string of an image.

Use PrinterTextParserImg.bitmapToHexadecimalString to convert Drawable, BitmapDrawable or Bitmap to hexadecimal string.

<img>hexadecimal string of an image</img>
⚠ WARNING ⚠ : This tag has several constraints :

A line that contains <img></img> can have only one alignment tag and it must be at the beginning of the line.
<img> must be directly preceded by nothing or an alignment tag ([L][C][R]).
</img> must be directly followed by a new line \n.
You can't write text on a line that contains <img></img>.
Barcode
<barcode></barcode> tag allows you to print a barcode. Inside the tag you need to write the code number to print.

<barcode>451278452159</barcode> : (12 numbers)
Prints a EAN13 barcode (height: 10mm, width: ~70% printer width, text: displayed below).
<barcode type='ean8'>4512784</barcode> : (7 numbers)
Prints a EAN8 barcode (height: 10mm, width: ~70% printer width, text: displayed below).
<barcode type='upca' height='20'>4512784521</barcode> : (11 numbers)
Prints a UPC-A barcode (height: 20mm, width: ~70% printer width, text: displayed below).
<barcode type='upce' height='25' width='50' text='none'>512789</barcode> : (6 numbers)
Prints a UPC-E barcode (height: 25mm, width: ~50mm, text: hidden).
<barcode type='128' width='40' text='above'>DantSu</barcode> : (string)
Prints a barcode 128 (height: 10mm, width: ~40mm, text: displayed above).
⚠ WARNING ⚠ : This tag has several constraints :

A line that contains <barcode></barcode> can have only one alignment tag and it must be at the beginning of the line.
<barcode> must be directly preceded by nothing or an alignment tag ([L][C][R]).
</barcode> must be directly followed by a new line \n.
You can't write text on a line that contains <barcode></barcode>.
QR Code
<qrcode></qrcode> tag allows you to print a QR code. Inside the tag you need to write the QR code data.

<qrcode>http://www.developpeur-web.dantsu.com/</qrcode> : Prints a QR code with a width and height of 20 millimeters.
<qrcode size='25'>123456789</qrcode> : Prints a QR code with a width and height of 25 millimeters.
⚠ WARNING ⚠ : This tag has several constraints :

A line that contains <qrcode></qrcode> can have only one alignment tag and it must be at the beginning of the line.
<qrcode> must be directly preceded by nothing or an alignment tag ([L][C][R]).
</qrcode> must be directly followed by a new line \n.
You can't write text on a line that contains <qrcode></qrcode>.