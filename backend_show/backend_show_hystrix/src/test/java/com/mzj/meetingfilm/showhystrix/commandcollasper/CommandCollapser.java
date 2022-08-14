package com.mzj.meetingfilm.showhystrix.commandcollasper;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.assertj.core.util.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *  请求合并处理对象
 *  */
public class CommandCollapser extends HystrixCollapser<List<String>,String,Integer> {
    private Integer id;

    public CommandCollapser(Integer id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("CommandCollapser")));
        this.id = id;
    }

    // 获取请求参数
    @Override
    public Integer getRequestArgument() {
        return null;
    }

    // 批量业务处理
    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collapsedRequests) {
        return new BatchCommand(collapsedRequests);
    }

    // 批量处理结果与请求业务之间映射关系处理
    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, Integer>> collapsedRequests) {
        int counts = 0;

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collapsedRequests.iterator();
        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> response = iterator.next();

            String result = batchResponse.get(counts++);

            response.setResponse(result);
        }

    }

    class BatchCommand extends HystrixCommand<List<String>> {

        private Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collapsedRequests;

        public BatchCommand(Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collapsedRequests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")));
            this.collapsedRequests = collapsedRequests;
        }

        @Override
        protected List<String> run() throws Exception {
            System.err.println("currentThread :" + Thread.currentThread().getName());
            List<String> result = Lists.newArrayList();

            if (collapsedRequests.isEmpty())
                throw new Exception();

            Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collapsedRequests.iterator();
            while (iterator.hasNext()) {
                HystrixCollapser.CollapsedRequest<String, Integer> request = iterator.next();

                Integer reqParam = request.getArgument();  // 获取元素

                // 具体业务逻辑
                result.add("Mooc req :" + reqParam);
            }

            return result;
        }
    }
}