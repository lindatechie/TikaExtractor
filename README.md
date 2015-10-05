# TikaExtractor
Extract "Text" content from a binary file (ex. doc, xls, ppt, pdf, eml, etc) to 2 output files. First output file is the "text" content with filename : "Source_filename.content.txt". Second output file is the metadata of the source file with filename: "source_filename.metadata.txt"

Please following license agreement listed in http://apache.tika.org

###Use Case Tested
1. Microsoft Office suite (ie. word, excel, power point, one note)
2. PDF
3. EML with and without attachments; and nested attachment

###Version Tested
1. OpenJDK 1.7.x
2. Apache Tika library v1.9

###Usage Example
java -jar TikaExtractor.jar full_path_directory/source_binary_filename full_path_output_directory

Ex. java -jar TikaExtractor.jar in/test1.pdf out
