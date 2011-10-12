import io.Source
import java.io.File

val messagesfile: File = new File(args(0))
val lines: List[String] = Source.fromFile(messagesfile).getLines().toList
var usedLines: List[String] = Nil;

val target: File = new File(args(1))
for (line <- lines){
  if (isUsed(target, line)){
    usedLines = line::usedLines;
  }
}

for (line <- usedLines){
  println(line)
}


def isUsed(file: File, s: String): Boolean = {
  var res: Boolean = false;
  val toMatch = s.split("=")(0)
  if (file.isDirectory) {
    for (f <- file.listFiles()){
      if (!f.equals(messagesfile)){
        res = isUsed(f, s);
      }
    }
  } else {
    val lines: List[String] = Source.fromFile(file).getLines().toList
    for (line <- lines){
      if (line.matches(".*"+toMatch+".*")){
        res = true;
      }
    }
  }
  return res;
}
