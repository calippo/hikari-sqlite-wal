package powerpuffs

import scala.concurrent.ExecutionContext.Implicits.global

import slick.driver.SQLiteDriver.api._

object Main extends App {
  DBModule.db.run(Powerpuffs.schema.create)
}

case class Powerpuff(id: Int, powerpuffCode: Int, name: String)
object Powerpuffs extends TableQuery[Powerpuffs](new Powerpuffs(_))
class Powerpuffs(tag: Tag) extends Table[Powerpuff](tag, "powerpuffs") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def powerpuffCode = column[Int]("powerpuffCode")
  def name = column[String]("name")

  def * = (id, powerpuffCode, name) <> (Powerpuff.tupled, Powerpuff.unapply)

  def idx1 = index("idx1", (name), unique = false)
}

object DBModule {
  Class.forName("org.sqlite.JDBC")

  val dbUrl = "jdbc:sqlite:db.sqlite"

  val configCP = new com.zaxxer.hikari.HikariConfig()
  configCP.setJdbcUrl(dbUrl)

  configCP.setMinimumIdle(1)
  configCP.setMaximumPoolSize(3)

  configCP.setConnectionTestQuery("SELECT 1")
  configCP.addDataSourceProperty("dataSource.journal_mode", "wal")
  val ds = new com.zaxxer.hikari.HikariDataSource(configCP)

  val executor = slick.util.AsyncExecutor("executor", numThreads = 3, queueSize = 1000)
  val db = Database.forDataSource(ds, executor)
}
