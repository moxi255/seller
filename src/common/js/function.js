var db = require('./sql.js')
export var login = function (userName, password) {
  db.sql('select * from T1', function (err, result) {
    if (err) {
      console.log(err)
      return
    }
    console.log('===', result)
  })
}
