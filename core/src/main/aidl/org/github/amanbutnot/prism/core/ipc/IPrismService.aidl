package org.github.amanbutnot.prism.core.ipc;
import org.github.amanbutnot.prism.core.model.PrismLog;
interface IPrismService{
String getPrismVersion();
List<PrismLog> getLogs();
}