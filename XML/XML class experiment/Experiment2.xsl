<?xml version="1.0" encoding="utf-8"?>
<!-- Edited by JoeMob-->
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                  *{ font-family:Sarasa Mono Slab SC;
                    margin-top:1%;
                   }
                  body{text-align:center;}
                  .MemberTable,.MemberCountTable{
                      margin:auto; border: 2px solid rgba(50,180,255,100); border-collapse: collapse; text-align:center;
                  }
                  .TableTitle{
                    background-color: rgba(50,180,255,100); text-align: center; color: white;
                  }
                  th,td{
                      border: 2px solid rgba(50,180,255,100); border-collapse: collapse;width:100px;
                  }
                </style>
            </head>
            <body>
                <div class="ListName">
                    <xsl:value-of select="MemberList/@ListName"/>
                </div>
                <div class="ListMaker">
                    List made by <xsl:value-of select="MemberList/ListMaker"/>
                </div>
                <div class="Members">
                    <table class="MemberTable">
                        <tr class="TableTitle">
                            <th>Name</th>
                            <th>Age</th>
                            <th>Birthday</th>
                            <th>Gender</th>
                        </tr>
                        <xsl:for-each select="MemberList/Member">
                            <tr>
                                <td>
                                    <xsl:value-of select="Name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="Age"/>
                                </td>
                                <td>
                                    <xsl:value-of select="Birthday"/>
                                </td>
                                <td>
                                    <xsl:value-of select="Gender"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </div>
                <div class="MemberCount">
                    <table class="MemberCountTable">
                        <tr class="TableTitle">
                            <th>All</th>
                            <th>Male</th>
                            <th>Female</th>
                        </tr>
                        <tr>
                            <td>
                                <xsl:value-of select="MemberList/MemberCount/All"/>
                            </td>
                            <td>
                                <xsl:value-of select="MemberList/MemberCount/Male"/>
                            </td>
                            <td>
                                <xsl:value-of select="MemberList/MemberCount/Female"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="ListDate">
                        List made on <xsl:value-of select="MemberList/ListDate"/>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>