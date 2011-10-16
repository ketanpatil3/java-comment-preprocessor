package com.igormaznitsa.jcpreprocessor.expression.functions;

import com.igormaznitsa.jcpreprocessor.expression.Expression;
import com.igormaznitsa.jcpreprocessor.expression.Value;
import java.io.File;
import org.w3c.dom.Element;

public final class FunctionXML_GETATTRIBUTE extends AbstractXMLFunction {

    @Override
    public String getName() {
        return "xml_getattribute";
    }

    public void execute(File currentFile, Expression _stack, int _index) {
       if (!_stack.areThereTwoValuesBefore(_index)) throw new IllegalStateException("Operation XML_GETATTRIBUTE needs two operands");

        Value _val1 = (Value)_stack.getItemAtPosition(_index-1);
        _index--;
        _stack.removeElementAt(_index);

        Value _val0 = (Value)_stack.getItemAtPosition(_index-1);
        _index--;
        _stack.removeElementAt(_index);

        Element p_element = null;
        String s_attribute = "";

        switch (_val0.getType())
        {
            case INT:
                {
                    long l_index  = ((Long) _val0.getValue()).longValue();

                    p_element = getXmlElementForIndex((int)l_index);

                };break;
            default :
                throw new IllegalArgumentException("Function XML_GETATTRIBUTE needs INTEGER type as the first operand");
        }

        switch (_val1.getType())
        {
            case STRING:
                {
                    s_attribute  = ((String) _val1.getValue());

                };break;
            default :
                throw new IllegalArgumentException("Function XML_GETATTRIBUTE needs STRING type as the second operand");
        }

        String s_value = p_element.getAttribute(s_attribute);

        _stack.setItemAtPosition(_index, new Value((Object)s_value));
    }
      @Override
    public int getArity() {
        return 2;
    }
    
  
}
