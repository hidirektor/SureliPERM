package com.infumia.t3sl4.util.reflection;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.Mapped;
import org.cactoos.text.TextOf;
import org.jetbrains.annotations.NotNull;

public class LoggerOf extends Logger {
   @NotNull
   private final String prefix;

   private LoggerOf(@NotNull String prefix) {
      super(prefix.replaceAll(", ", "#"), (String)null);
      this.prefix = "[" + this.getName() + "] ";
      this.setParent(Logger.getLogger(prefix));
      this.setLevel(Level.ALL);
   }

   public LoggerOf(@NotNull Class<?>... classes) {
      this((new TextOf(new Mapped<>(Class::getSimpleName, new IterableOf<>(classes)))).toString());
   }

   public void log(@NotNull LogRecord logRecord) {
      logRecord.setMessage(this.prefix + logRecord.getMessage());
      super.log(logRecord);
   }
}
