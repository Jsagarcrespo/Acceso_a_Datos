<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>

<head>

  <style>
		table
		{
		border:2px solid black; 
		border-collapse: collapse;
		}
		
		th
		{
			border:2px solid black; 
			padding:8px 8px 8px 8px;
			color: red;
		}
		
		td
		{
			border:2px solid black; 
			padding:8px 8px 8px 8px;
			color: green;
		}
		</style>
		
	</head>

  <h2>Lista de canciones</h2>
  <table>
    <tr>
      <th style="text-align:left">ID</th>
      <th style="text-align:left">Año</th>
	  <th style="text-align:left">Titulo</th>
      <th style="text-align:left">Artista</th>
	  <th style="text-align:left">Duracion</th>
      <th style="text-align:left">Española?</th>
    </tr>
    <xsl:for-each select="Canciones/Cancion">
    <tr>
      <td style="color: black"><b><xsl:value-of select="Id"/></b></td>
      <td><xsl:value-of select="Ano"/></td>
	  <td><xsl:value-of select="Titulo"/></td>
      <td><xsl:value-of select="Artista"/></td>
	  <td><xsl:value-of select="Duracion"/></td>
      <td><xsl:value-of select="Espanola"/></td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>


