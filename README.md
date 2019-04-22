# work notes
personal accumulation

#幂等控制
全局幂等表(aqc_pre_global_unique)
主键：request_id
request_id: String(merchantId+merchantTranstionId+bizType)
sharding_key: Hash(request_id)
biz_sharding_key: userid
db_distinguish_key: (true/false)
业务幂等表(aqc_pre_unique)
主键：biz_type+merchant_request_id+merchant_id