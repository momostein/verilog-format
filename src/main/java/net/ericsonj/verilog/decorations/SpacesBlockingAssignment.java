package net.ericsonj.verilog.decorations;

import net.ericsonj.util.StringHelper;
import net.ericsonj.verilog.FileFormat;

/**
 *
 * @author ericson
 * @author Momostein
 */
public class SpacesBlockingAssignment extends AbstractLineDecoration {

    @Override
    public String decorateLine(FileFormat format, String line, int lineIndex) {
        if (format.getSpacesBlockingAssignment() == 0) {
            return line;
        }
        // use lookbehind and lookahead to match only single =
        if (line.matches(".*(?<![!<>=])=(?!=).*")) {
            String spaces = StringHelper.getSpaces(format.getSpacesBlockingAssignment());
            String aux = line.replaceAll("\\s*(?<![!<>=])=(?!=)\\s*", spaces + "=" + spaces);   
            // aux = aux.replaceAll("=[ ]*=", "=="); // FIX in case ==
            return aux;
        }
        return line;
    }

    @Override
    public boolean inBlockComment() {
        return false;
    }

}
