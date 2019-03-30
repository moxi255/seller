
var mssql = require('mssql')
var db = {}
var config = {
  user: 'sa',
  password: '*******',
  server: '127.0.0.1',
  database: 'Test',
  port: 1433,
  options: {
    encrypt: true // windows on
  },
  pool: {
    min: 0,
    max: 10,
    idleTimeoutMillis: 3000
  }
}

// 执行sql,返回数据.
db.sql = function (sql, callBack) {
  var connection = new mssql.ConnectionPool(config, function (err) {
    if (err) {
      console.log(err)
      return
    }
    var ps = new mssql.PreparedStatement(connection)
    ps.prepare(sql, function (err) {
      if (err) {
        console.log(err)
        return
      }
      ps.execute('', function (err, result) {
        if (err) {
          console.log(err)
          return
        }

        ps.unprepare(function (err) {
          if (err) {
            console.log(err)
            callBack(err, null)
            return
          }
          callBack(err, result)
        })
      })
    })
  })
}
module.exports = db
