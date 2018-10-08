package com.audible.invoker;

import com.audible.migrate.FilesToDiffGenerator;
import com.audible.migrate.MetadataGenerator;
import com.audible.model.Table;
import com.audible.utils.XmlUtil;
import java.util.List;

public class FilesToDiffInvoker {

  public static void main(String arg[]) throws Exception {
    List<Table> tables = XmlUtil.getTables("DATABASE_TABLE.xml");
    FilesToDiffGenerator filesToDiffGenerator = new FilesToDiffGenerator();
    MetadataGenerator metadataGenerator = new MetadataGenerator();

    try {
      filesToDiffGenerator.generateTableDiffs(tables);
      metadataGenerator.generateTableMetaData(tables);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

}
