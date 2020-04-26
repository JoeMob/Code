<?xml version="1.0" encoding="utf-8" ?>
<!-- Edited by JoeMob-->
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    *{ font-family:Sarasa Mono Slab SC;}
                    .TableTitle{ background-color: rgba(50,180,255,100); text-align: center; color: white;}
                    table, th, td { border: 2px solid rgba(50,180,255,100); border-collapse: collapse;}
                </style>
            </head>
            <body>
                <table>
                    <tr class="TableTitle">
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                    <xsl:for-each select="XSLTElement/element">
                        <xsl:sort select="name" />
                        <tr>
                            <td>
                                <xsl:value-of select="name" />
                            </td>
                            <td>
                                <xsl:value-of select="description" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>