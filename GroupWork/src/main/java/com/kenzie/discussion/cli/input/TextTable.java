package com.kenzie.discussion.cli.input;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility for rendering a table from a given List of headers and a List of rows ({@code List<String>}).
 *
 * The table will look something like this:
 * <pre>
 *  --------------------------------------------
 * | header 1 | header 2                        |
 *  ============================================
 * | field 1  | longer field extending column 2 |
 *  --------------------------------------------
 * | another  | row                             |
 *  --------------------------------------------
 * </pre>
 *
 * The header row is the first row of fields displayed, and specified in the constructor argument, {@code headers}.
 * The rows of data are specified in the constructor argument, rows as a List of Lists of Strings.
 * If any row has more entries in it than the header row, then IllegalArgumentException will be thrown.
 *
 * The header divider is the string of characters displayed immediately below the header fields.
 * All other horizontal dividers are the row divider.
 *
 * The character used below the header row can be configured with setHeaderDividerChar().
 * The character used for other row separators can be configured with setRowDividerChar().
 * The String used to separate columns (can be more than single char) can be configured with setColumnDivider().
 *
 * Example:
 *
 * <pre>{@code
 *   List<String> headers = Arrays.asList("Date", "Name", "Breakfast");
 *   List<List<String>> rows = new ArrayList<>();
 *
 *   rows.add(Arrays.asList("2019-08-04", "Abby O.", "Oats");
 *   rows.add(Arrays.asList("2019-08-04", "Frei J.", "Chips");
 *   rows.add(Arrays.asList("2019-08-05", "Abby O.", "Cap'n Crunch");
 *
 *   TextTable tt = new TextTable(headers, rows);
 *   tt.setHeaderDividerChar('#');
 *
 *   System.out.println(tt);
 * }</pre>
 *
 * will generate the output:
 *
 * <pre>
 *  -------------------------------------
 * | Date       | Name    | Breakfast    |
 *  #####################################
 * | 2019-08-04 | Abby O. | Oats         |
 *  -------------------------------------
 * | 2019-08-04 | Frei J. | Chips        |
 *  -------------------------------------
 * | 2019-08-05 | Abby O. | Cap'n Crunch |
 *  -------------------------------------
 * </pre>
 *
 * Future work:
 * <ul>
 *     <li>
 *         Allow multi-line entries in table (a row takes up more that one line of text
 *     </li>
 *     <li>
 *         Automatically add line breaks inside entries (at whitespace) to fit table to a given width, even if newlines
 *     don't exist in entry
 *     </li>
 *     <li>
 *         Use a distinct member character/string for side border character and top/bottom border character rather than
 *         treating them the same as column boundary and row boundary, respectively. Let both of these be configurable.
 *     </li>
 * </ul>
 */
public class TextTable {

    private static final char DEFAULT_ROW_DIVIDER_CHAR = '-';
    private static final char DEFAULT_HEADER_DIVIDER_CHAR = '=';
    private static final String DEFAULT_COLUMN_DIVIDER = "|";

    private char rowDividerChar = DEFAULT_ROW_DIVIDER_CHAR;
    private char headerDividerChar = DEFAULT_HEADER_DIVIDER_CHAR;
    private String columnDivider = DEFAULT_COLUMN_DIVIDER;
    private String headerDivider;
    private String rowDivider;
    private Integer fullTableWidth;

    private List<String> headers;
    private List<List<String>> rows;
    private List<Integer> columnWidths;

    /**
     * Returns a new renderer, which requires a List of header fields and a List of
     * rows, each row being a {@code List<String>}s. Rows don't need to contain an entry for all
     * columns, but the table will be filled left-to-right, so a row with fewer than the total
     * number of columns will fill with empty entries on the right.
     *
     * @param headers {@code List<String>}, each {@code String} containing text of one header column
     * @param rows {@code List<List<String>>}, each {@code List<String>} entry in outer {@code List}
     *                                       representing a row in the table, and each {@code String}
     *                                       being the contents of a given entry in the table.
     */
    public TextTable(List<String> headers, List<List<String>> rows) {
        validateTableData(headers, rows);
        this.headers = sanitizeRow(headers);
        this.rows = sanitizeRows(rows);
    }

    /**
     * Sets the row divider character, overriding the default.
     *
     * @param divider The character to repeat to create the row dividing lines
     * @return TextTable
     */
    public TextTable setRowDividerChar(char divider) {
        this.rowDividerChar = divider;
        return this;
    }

    /**
     * Sets the header divider character, overriding the default.
     *
     * @param divider The character to repeat to create the header dividing line
     * @return TextTable
     */
    public TextTable setHeaderDividerChar(char divider) {
        this.headerDividerChar = divider;
        return this;
    }

    /**
     * Sets the column divider string, overriding the default.
     *
     * @param divider The String to insert between columns fields
     * @return TextTable
     */
    public TextTable setColumnDivider(String divider) {
        this.columnDivider = divider;
        return this;
    }

    /**
     * Returns the table in {@code String} form.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\n").append(renderHeader());
        for (List<String> row : rows) {
            builder.append(renderRow(row))
                    .append(rowDivider());
        }

        return builder.toString();
    }

    private String renderHeader() {
        return new StringBuilder(rowDivider())
                .append(renderRow(headers))
                .append(headerDivider())
                .toString();
    }

    private String renderRow(List<String> fields) {
        // fill in columns missing from this row's fields
        List<String> filledRow = new ArrayList<>(fields);
        for (int i = 0; i < numColumns() - fields.size(); i++) {
            filledRow.add("");
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < filledRow.size(); i++) {
            String fieldFormatString = "  ";

            if (columnWidths().get(i) > 0) {
                // '%-5s' is a string of width 5 with left-justification
                fieldFormatString = String.format(" %%-%ds ", columnWidths().get(i));
            }
            builder.append(String.format("%s" + fieldFormatString, columnDivider, filledRow.get(i)));
        }
        builder.append(columnDivider).append('\n');

        return builder.toString();
    }

    private int numColumns() {
        return columnWidths().size();
    }

    // compute the full width of table in characters including all column dividers, spaces etc.
    private int fullTableWidth() {
        if (null == fullTableWidth) {

            // sum of field widths + (2 spaces + divider per column) * (number of columns) + last divider
            fullTableWidth = columnWidths().stream().mapToInt(Integer::valueOf).sum() +
                    (2 + columnDivider.length()) * numColumns() +
                    columnDivider.length();
        }

        return fullTableWidth;
    }

    // (lazily) compute the width of each column, taking the maximum width of that column's header
    // or field from the rows.
    private List<Integer> columnWidths() {
        if (null == columnWidths) {
            columnWidths = headers.stream()
                    .map(String::length)
                    .collect(Collectors.toList());

            for (List<String> row : rows) {
                for (int i = 0; i < row.size(); i++) {
                    String field = row.get(i);

                    if (i >= columnWidths.size()) {
                        columnWidths.add(i, field.length());
                    } else if (field.length() > columnWidths.get(i)) {
                        columnWidths.set(i, field.length());
                    }
                }
            }
        }

        return columnWidths;
    }

    // (lazily) compute the divider line below the header line
    private String headerDivider() {
        if (null == headerDivider) {
            headerDivider = dividerLine(headerDividerChar);
        }

        return headerDivider;
    }

    // (lazily) compute the row divider line
    private String rowDivider() {
        if (null == rowDivider) {
            rowDivider = dividerLine(rowDividerChar);
        }

        return rowDivider;
    }

    // compute a divider line (either below header row or elsewhere)
    private String dividerLine(char dividerChar) {
        String divider = repeatStringNTimes(String.valueOf(dividerChar),
                fullTableWidth() - 2 * columnDivider.length());
        return String.format("%" +
                        columnDivider.length() +
                        "s%s%" +
                        columnDivider.length() +
                        "s%c",
                "", divider, "", '\n');
    }

    // force all null fields in all rows to ""
    private List<List<String>> sanitizeRows(List<List<String>> rawRows) {
        return new ArrayList<>(rawRows)
                .stream()
                .map(this::sanitizeRow)
                .collect(Collectors.toList());
    }

    // force all null fields in row to ""
    private List<String> sanitizeRow(List<String> rawRow) {
        return new ArrayList<>(rawRow)
                .stream()
                .map(s -> null == s ? "" : s)
                .collect(Collectors.toList());
    }

    // determine if input data are valid; throw IllegalArgumentException if not.
    // - if any row has more entries than header does
    private void validateTableData(List<String> candidateHeaders, List<List<String>> candidateRows) {
        for (List<String> row : candidateRows) {
            if (row.size() > candidateHeaders.size()) {
                throw new IllegalArgumentException(
                        String.format("No rows can have more entries than the number of entry in headers. " +
                                        "At least one row had more entries than the number of headers (%d). row: %s",
                                candidateHeaders.size(),
                                row)
                );
            }
        }
    }

    private String repeatStringNTimes(String sequence, int repeatCount) {
        return new String(new char [repeatCount]).replace("\0", sequence);
    }

}

