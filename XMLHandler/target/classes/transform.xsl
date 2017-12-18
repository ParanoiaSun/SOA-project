<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="xs"
                xmlns="http://jw.nju.edu.cn/schema"
                xmlns:tns="http://jw.nju.edu.cn/schema">

    <xsl:variable name="courses" as="xs:string*" select="distinct-values(/tns:学生列表//tns:课程成绩/@课程编号)"/>
    <xsl:variable name="students" select="/tns:学生列表"/>
    <xsl:template match="/">
        <xsl:element name="课程成绩列表">
            <xsl:for-each select="$courses">
                <xsl:call-template name="part-of-course">
                    <xsl:with-param name="course" select="."/>
                    <xsl:with-param name="type" select="'平时成绩'"/>
                </xsl:call-template>
                <xsl:call-template name="part-of-course">
                    <xsl:with-param name="course" select="."/>
                    <xsl:with-param name="type" select="'期末成绩'"/>
                </xsl:call-template>
                <xsl:call-template name="part-of-course">
                    <xsl:with-param name="course" select="."/>
                    <xsl:with-param name="type" select="'总评成绩'"/>
                </xsl:call-template>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

    <xsl:template name="part-of-course">
        <xsl:param name="type"/>
        <xsl:param name="course"/>
        <xsl:element name="课程成绩">
            <xsl:attribute name="成绩性质">
                <xsl:value-of select="$type"/>
            </xsl:attribute>
            <xsl:attribute name="课程编号">
                <xsl:value-of select="$course"/>
            </xsl:attribute>
            <xsl:for-each select="$students//tns:课程成绩[@成绩性质=$type and @课程编号=$course]">
                <xsl:sort select="tns:成绩/tns:得分" order="descending"/>
                <xsl:element name="成绩">
                    <xsl:element name="学号">
                        <xsl:value-of select="tns:成绩/tns:学号"/>
                    </xsl:element>
                    <xsl:element name="得分">
                        <xsl:value-of select="tns:成绩/tns:得分"/>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>