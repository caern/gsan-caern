/*
 * An XML document type.
 * Localname: atualizarCacheResponse
 * Namespace: http://fachada.webservices.gateway.neurotech.com.br
 * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument
 *
 * Automatically generated - do not modify.
 */
package gcom.integracao.webservice.neurotech.gateway.webservices.fachada;


/**
 * A document containing one atualizarCacheResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
 *
 * This is a complex type.
 */
public interface AtualizarCacheResponseDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AtualizarCacheResponseDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("atualizarcacheresponse210fdoctype");
    
    /**
     * Gets the "atualizarCacheResponse" element
     */
    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse getAtualizarCacheResponse();
    
    /**
     * Sets the "atualizarCacheResponse" element
     */
    void setAtualizarCacheResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse atualizarCacheResponse);
    
    /**
     * Appends and returns a new empty "atualizarCacheResponse" element
     */
    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse addNewAtualizarCacheResponse();
    
    /**
     * An XML atualizarCacheResponse(@http://fachada.webservices.gateway.neurotech.com.br).
     *
     * This is a complex type.
     */
    public interface AtualizarCacheResponse extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AtualizarCacheResponse.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("atualizarcacheresponsee33felemtype");
        
        /**
         * Gets the "return" element
         */
        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS getReturn();
        
        /**
         * Tests for nil "return" element
         */
        boolean isNilReturn();
        
        /**
         * True if has "return" element
         */
        boolean isSetReturn();
        
        /**
         * Sets the "return" element
         */
        void setReturn(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS xreturn);
        
        /**
         * Appends and returns a new empty "return" element
         */
        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS addNewReturn();
        
        /**
         * Nils the "return" element
         */
        void setNilReturn();
        
        /**
         * Unsets the "return" element
         */
        void unsetReturn();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse newInstance() {
              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument newInstance() {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
