db.auth('user', 'test')

db = db.getSiblingDB('quinielator')

db.tests.createIndex({ "address.zip": 1 }, { unique: false });

db.tests.insert({ "address": { "city": "Paris", "zip": "123" }, "name": "Mike", "phone": "1234" });
db.tests.insert({ "address": { "city": "Marsel", "zip": "321" }, "name": "Helga", "phone": "4321" });

db.createUser({
  user: 'mikel',
  pwd: 'test',
  roles: [
    {
      role: 'dbOwner',
      db: 'quinielator',
    },
  ],
});